package chatroom.client.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import chatroom.protocol.response.UploadFileResponsePacket;

/**
 * @param: none
 * @description:
 * @author: KingJ
 * @create: 2019-09-09 16:23
 **/
@ChannelHandler.Sharable
public class UploadFileResponseHandler extends SimpleChannelInboundHandler<UploadFileResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, UploadFileResponsePacket uploadFileResponsePacket) throws Exception {
        System.out.println(uploadFileResponsePacket.getMsg());
        System.out.println("Receive " +  uploadFileResponsePacket.getByteRead() + " bytes!");
    }
}
