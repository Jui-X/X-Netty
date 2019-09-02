package netty.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import netty.protocol.response.QuitGroupResponsePacket;

/**
 * @param: none
 * @description:
 * @author: KingJ
 * @create: 2019-09-02 21:21
 **/
public class QuitGroupResponseHandler extends SimpleChannelInboundHandler<QuitGroupResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QuitGroupResponsePacket quitGroupResponsePacket) throws Exception {
        if (quitGroupResponsePacket.isSuccess()) {
            System.out.println("quit group " + quitGroupResponsePacket.getGroupName() + " successfully");
        } else {
            System.out.println("quit group " + quitGroupResponsePacket.getGroupName() + " failed");
        }
    }
}
