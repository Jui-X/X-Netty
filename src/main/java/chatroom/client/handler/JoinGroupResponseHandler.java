package chatroom.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import chatroom.protocol.response.JoinGroupResponsePacket;

import java.util.List;

/**
 * @param: none
 * @description:
 * @author: KingJ
 * @create: 2019-09-02 20:48
 **/
public class JoinGroupResponseHandler extends SimpleChannelInboundHandler<JoinGroupResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupResponsePacket joinGroupResponsePacket) throws Exception {
        if (joinGroupResponsePacket.isSuccess()) {
            String groupName = joinGroupResponsePacket.getGroupName();
            List<String> userList = joinGroupResponsePacket.getUserList();

            System.out.println("u have joined group " + groupName + " successfully!");
            System.out.println("group members includes: " + userList);
        } else {
            System.out.println("join group " + joinGroupResponsePacket.getGroupName() + " failed!");
        }
    }
}
