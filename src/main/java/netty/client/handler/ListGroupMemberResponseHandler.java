package netty.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import netty.protocol.response.ListGroupMemberResponsePacket;

/**
 * @param: none
 * @description:
 * @author: KingJ
 * @create: 2019-09-02 21:34
 **/
public class ListGroupMemberResponseHandler extends SimpleChannelInboundHandler<ListGroupMemberResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ListGroupMemberResponsePacket listGroupMemberResponsePacket) throws Exception {
        System.out.println("group member include: " + listGroupMemberResponsePacket.getSessionList());
    }
}
