package netty.server.handler;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import netty.protocol.request.LoginRequestPacket;
import netty.protocol.response.LoginResponsePacket;
import netty.session.Session;
import netty.util.SessionUtil;

import java.util.UUID;

/**
 * @param: none
 * @description:
 * @author: KingJ
 * @create: 2019-08-28 22:12
 **/
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket loginRequestPacket) throws Exception {
        System.out.println("receive login request");

        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setVersion(loginRequestPacket.getVersion());
        loginResponsePacket.setUserName(loginRequestPacket.getUserName());

        // 校验登录
        if (valid(loginRequestPacket)) {
            String userId = getUserId();
            String userName = loginRequestPacket.getUserName();
            loginResponsePacket.setUserId(userId);
            loginResponsePacket.setSuccess(true);
            SessionUtil.bindSession(new Session(userId, userName), ctx.channel());
            System.out.println(userId + "-" + userName + " login successfully");
        } else {
            loginResponsePacket.setSuccess(false);
            loginResponsePacket.setReason("登录校验失败，请检查账号或密码是否正确。");
        }

        ctx.channel().writeAndFlush(loginResponsePacket);
    }

    private boolean valid(LoginRequestPacket requestPacket) {
        return true;
    }

    private static String getUserId() {
        return UUID.randomUUID().toString().split("-")[0];
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        SessionUtil.unBindSession(ctx.channel());
    }
}
