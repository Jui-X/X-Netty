package netty.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import netty.protocol.response.JoinGroupResponsePacket;

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
            System.out.println("u have joined group " + joinGroupResponsePacket.getGroupName() + " successfully!");
        } else {
            System.out.println("join group " + joinGroupResponsePacket.getGroupName() + " failed!");
        }
    }
}
