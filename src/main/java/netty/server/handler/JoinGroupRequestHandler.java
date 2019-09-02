package netty.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import netty.protocol.request.JoinGroupRequestPacket;
import netty.protocol.response.JoinGroupResponsePacket;
import netty.util.SessionUtil;

/**
 * @param: none
 * @description:
 * @author: KingJ
 * @create: 2019-09-02 20:44
 **/
public class JoinGroupRequestHandler extends SimpleChannelInboundHandler<JoinGroupRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupRequestPacket joinGroupRequestPacket) throws Exception {
        // 获取群对应的channel group，然后将当前用户的channel加入进去
        String groupName = joinGroupRequestPacket.getGroupName();
        ChannelGroup channels = SessionUtil.getChannelGroup(groupName);
        channels.add(ctx.channel());

        JoinGroupResponsePacket joinGroupResponsePacket = new JoinGroupResponsePacket();

        joinGroupResponsePacket.setSuccess(true);
        joinGroupResponsePacket.setGroupName(joinGroupRequestPacket.getGroupName());

        ctx.channel().writeAndFlush(joinGroupResponsePacket);
    }
}
