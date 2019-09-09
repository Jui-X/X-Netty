package netty.server.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import netty.protocol.request.LoginRequestPacket;
import netty.protocol.response.LoginResponsePacket;
import netty.session.Session;
import netty.util.IDUtil;
import netty.util.SessionUtil;

/**
 * @param: none
 * @description:
 * @author: KingJ
 * @create: 2019-08-28 22:12
 **/
@ChannelHandler.Sharable
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {
    public static final LoginRequestHandler INSTANCE = new LoginRequestHandler();

    private LoginRequestHandler() {

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket loginRequestPacket) throws Exception {
        System.out.println("receive login request");

        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setVersion(loginRequestPacket.getVersion());
        loginResponsePacket.setUserName(loginRequestPacket.getUserName());

        // 校验登录
        if (valid(loginRequestPacket)) {
            String userId = IDUtil.randomId();
            String userName = loginRequestPacket.getUserName();
            loginResponsePacket.setUserId(userId);
            loginResponsePacket.setSuccess(true);
            SessionUtil.bindSession(new Session(userId, userName), ctx.channel());
            System.out.println(userId + "-" + userName + " login successfully");
        } else {
            loginResponsePacket.setSuccess(false);
            loginResponsePacket.setReason("login failed, plz check ur name and pwd!");
        }

        ctx.writeAndFlush(loginResponsePacket);
    }

    private boolean valid(LoginRequestPacket requestPacket) {
        return true;
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        SessionUtil.unBindSession(ctx.channel());
    }
}
