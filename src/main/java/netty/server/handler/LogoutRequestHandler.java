package netty.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import netty.protocol.request.LogoutRequestPacket;
import netty.protocol.response.LogoutResponsePacket;
import netty.util.SessionUtil;

/**
 * @param: none
 * @description:
 * @author: KingJ
 * @create: 2019-09-02 20:34
 **/
public class LogoutRequestHandler extends SimpleChannelInboundHandler<LogoutRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutRequestPacket msg) throws Exception {
        SessionUtil.unBindSession(ctx.channel());
        LogoutResponsePacket logoutResponsePacket = new LogoutResponsePacket();
        logoutResponsePacket.setSuccess(true);

        ctx.channel().writeAndFlush(logoutResponsePacket);
    }
}
