package chatroom.server.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import chatroom.protocol.request.ListGroupMemberRequestPacket;
import chatroom.protocol.response.ListGroupMemberResponsePacket;
import chatroom.session.Session;
import chatroom.util.SessionUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @param: none
 * @description:
 * @author: KingJ
 * @create: 2019-09-02 21:30
 **/
@ChannelHandler.Sharable
public class ListGroupMemberRequestHandler extends SimpleChannelInboundHandler<ListGroupMemberRequestPacket> {
    public static final ListGroupMemberRequestHandler INSTANCE = new ListGroupMemberRequestHandler();

    private ListGroupMemberRequestHandler() {

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ListGroupMemberRequestPacket listGroupMemberRequestPacket) throws Exception {
        // 获取群名以及对应的Channel Group
        String groupName = listGroupMemberRequestPacket.getGroupName();
        ChannelGroup channels = SessionUtil.getChannelGroup(groupName);

        // 遍历群成员的Channel，获取对应的Session即成员信息
        List<String> userList = new ArrayList<>();
        for (Channel channel : channels) {
            Session session = SessionUtil.getSession(channel);
            userList.add(session.getUserName());
        }

        // 构建成员列表写回响应信息
        ListGroupMemberResponsePacket listGroupMemberResponsePacket = new ListGroupMemberResponsePacket();

        listGroupMemberRequestPacket.setGroupName(groupName);
        listGroupMemberResponsePacket.setUserList(userList);

        ctx.writeAndFlush(listGroupMemberResponsePacket);
    }
}
