package netty.server.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import netty.protocol.request.QuitGroupRequestPacket;
import netty.protocol.response.QuitGroupResponsePacket;
import netty.util.SessionUtil;

/**
 * @param: none
 * @description:
 * @author: KingJ
 * @create: 2019-09-02 21:10
 **/
@ChannelHandler.Sharable
public class QuitGroupRequestHandler extends SimpleChannelInboundHandler<QuitGroupRequestPacket> {
    public static final QuitGroupRequestHandler INSTANCE = new QuitGroupRequestHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QuitGroupRequestPacket quitGroupRequestPacket) throws Exception {
        // 获取群对应Channel Group，并将当前用户的channel移除
        String groupName = quitGroupRequestPacket.getGroupName();
        ChannelGroup channels = SessionUtil.getChannelGroup(groupName);
        channels.remove(ctx.channel());

        QuitGroupResponsePacket quitGroupResponsePacket = new QuitGroupResponsePacket();

        quitGroupResponsePacket.setSuccess(true);
        quitGroupResponsePacket.setGroupName(groupName);

        ctx.writeAndFlush(quitGroupResponsePacket);
    }
}
