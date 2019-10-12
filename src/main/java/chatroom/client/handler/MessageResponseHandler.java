package chatroom.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import chatroom.protocol.response.MessageResponsePacket;

/**
 * @param: none
 * @description:
 * @author: KingJ
 * @create: 2019-08-28 22:29
 **/
public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageResponsePacket messageResponsePacket) throws Exception {
        String fromUserId = messageResponsePacket.getFromUserId();
        String fromUserName = messageResponsePacket.getFromUserName();
        System.out.println("receive msg: " + messageResponsePacket.getMessage() + " from: " + fromUserId + "-" + fromUserName);
    }
}
