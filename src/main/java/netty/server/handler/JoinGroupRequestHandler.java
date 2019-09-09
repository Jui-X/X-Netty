package netty.server.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import netty.protocol.request.JoinGroupRequestPacket;
import netty.protocol.response.JoinGroupResponsePacket;
import netty.session.Session;
import netty.util.SessionUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @param: none
 * @description:
 * @author: KingJ
 * @create: 2019-09-02 20:44
 **/
@ChannelHandler.Sharable
public class JoinGroupRequestHandler extends SimpleChannelInboundHandler<JoinGroupRequestPacket> {
    public static final JoinGroupRequestHandler INSTANCE = new JoinGroupRequestHandler();

    private JoinGroupRequestHandler() {

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupRequestPacket joinGroupRequestPacket) throws Exception {
        // 获取群对应的channel group，然后将当前用户的channel加入进去
        String groupName = joinGroupRequestPacket.getGroupName();
        ChannelGroup channels = SessionUtil.getChannelGroup(groupName);
        channels.add(ctx.channel());

        List<String> userList = new ArrayList<>();
        for (Channel channel : channels) {
            Session session = SessionUtil.getSession(channel);
            userList.add(session.getUserName());
        }

        JoinGroupResponsePacket joinGroupResponsePacket = new JoinGroupResponsePacket();

        joinGroupResponsePacket.setSuccess(true);
        joinGroupResponsePacket.setUserList(userList);
        joinGroupResponsePacket.setGroupName(joinGroupRequestPacket.getGroupName());

        ctx.writeAndFlush(joinGroupResponsePacket);
    }
}
