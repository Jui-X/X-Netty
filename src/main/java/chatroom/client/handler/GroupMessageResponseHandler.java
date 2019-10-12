package chatroom.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import chatroom.protocol.response.GroupMessageResponsePacket;

/**
 * @param: none
 * @description:
 * @author: KingJ
 * @create: 2019-09-06 11:04
 **/
public class GroupMessageResponseHandler extends SimpleChannelInboundHandler<GroupMessageResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMessageResponsePacket groupMessageResponsePacket) throws Exception {
        String fromGroupName = groupMessageResponsePacket.getFromGroupName();
        String fromUser = groupMessageResponsePacket.getFromUser();
        String message = groupMessageResponsePacket.getMessage();

        System.out.println("receive group [" + fromGroupName + "] " + "user " + fromUser + "'s message: " + message);
    }
}
