package chatroom.server.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import chatroom.protocol.request.GroupMessageRequestPacket;
import chatroom.protocol.response.GroupMessageResponsePacket;
import chatroom.util.SessionUtil;

/**
 * @param: none
 * @description:
 * @author: KingJ
 * @create: 2019-09-06 11:03
 **/
@ChannelHandler.Sharable
public class GroupMessageRequestHandler extends SimpleChannelInboundHandler<GroupMessageRequestPacket> {
    public static final GroupMessageRequestHandler INSTANCE = new GroupMessageRequestHandler();

    private GroupMessageRequestHandler() {

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMessageRequestPacket groupMessageRequestPacket) throws Exception {
        String groupName = groupMessageRequestPacket.getToGroup();

        GroupMessageResponsePacket groupMessageResponsePacket = new GroupMessageResponsePacket();
        groupMessageResponsePacket.setFromGroupName(groupName);
        groupMessageResponsePacket.setFromUser(SessionUtil.getSession(ctx.channel()).getUserName());
        groupMessageResponsePacket.setMessage(groupMessageRequestPacket.getMessage());

        // 拿到群名对应的ChannelGroup，向每个客户端发送信息
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupName);
        channelGroup.writeAndFlush(groupMessageResponsePacket);
    }
}
