package netty.server.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import netty.protocol.request.MessageRequestPacket;
import netty.protocol.response.MessageResponsePacket;
import netty.session.Session;
import netty.util.SessionUtil;

/**
 * @param: none
 * @description:
 * @author: KingJ
 * @create: 2019-08-28 22:13
 **/
@ChannelHandler.Sharable
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {
    public static final MessageRequestHandler INSTANCE = new MessageRequestHandler();

    private MessageRequestHandler() {

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket messageRequestPacket) throws Exception {
        // 拿到消息发送方的Session
        Session session = SessionUtil.getSession(ctx.channel());

        // 通过消息发送方的Session信息构造需要发送的消息
        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        messageResponsePacket.setFromUserId(session.getUserId());
        messageResponsePacket.setFromUserName(session.getUserName());
        messageResponsePacket.setMessage("server receive: " + messageRequestPacket.getMessage());

        // 获取消息接收方的Channel
        Channel toUserChannel = SessionUtil.getChannel(messageRequestPacket.getToUserName());

        if (toUserChannel != null && SessionUtil.isLogin(toUserChannel)) {
            toUserChannel.writeAndFlush(messageResponsePacket);
        } else {
            // TODO 缓存消息至本地内存或MQ
            System.out.println(session.getUserId() + " is not online");
        }
    }
}
