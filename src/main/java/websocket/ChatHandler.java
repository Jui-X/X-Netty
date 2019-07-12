package websocket;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import websocket.utils.JsonUtils;

/**
 * @param: none
 * @description: 处理消息的Handler
 *               TextWebSocketFrame: 在netty中，用于为websocket专门处理文本的对象，frame是消息的载体
 * @author: KingJ
 * @create: 2019-07-12 14:31
 **/
public class ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    // 用于记录和管理所有客户端的Channel
    private static ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) throws Exception {
        /**
         * 1.获取客户端发送过来的消息
         * 2.根据消息类型来判断，处理不同的业务
         *      2.1 当websocket第一次open的时候，初始化channel，把用户的channel和userId关联
         *      2.2 聊天类型的消息，把聊天记录保存到数据库，同时标记消息的签收状态[未签收]
         *      2.3 签收消息类型，针对具体的消息进行签收，修改数据库中对应消息的签收状态[已签收]
         *      2.4 心跳类型的消息
         */
        // 获取客户端传过来的消息
        String content = textWebSocketFrame.text();
        Channel channel = channelHandlerContext.channel();
        // 1.获取客户端发送过来的消息
        DataContent dataContent = JsonUtils.jsonToPojo(content, DataContent.class);
        Integer action = dataContent.getAction();

        // 2.根据消息类型来判断，处理不同的业务
        if (action == MsgActionEnum.CONNECT.type) {
            // 2.1 当websocket第一次open的时候，初始化channel，把用户的channel和userId关联
            String senderID = dataContent.getMsg().getSenderID();
            UserChannelRel.put(senderID, channel);
        } else if (action == MsgActionEnum.CHAT.type) {
            // 2.2 聊天类型的消息，把聊天记录保存到数据库，同时标记消息的签收状态[未签收]
            ChatMsg msg = dataContent.getMsg();
            String msgTxt = msg.getMsg();
            String receiverID = msg.getReceiverID();
            String senderID = msg.getSenderID();

            // 发送消息
            // 从全局用户Channel关系中获取接收者channel
            Channel receiverChannel = UserChannelRel.get(receiverID);
            if (receiverChannel != null) {
                Channel findChannel = clients.find(receiverChannel.id());
                if (findChannel != null) {
                    receiverChannel.writeAndFlush(new TextWebSocketFrame(JsonUtils.objectToJson(msg)));
                }
            }
        } else if (action == MsgActionEnum.SIGNED.type) {
            // 2.3 签收消息类型，针对具体的消息进行签收，修改数据库中对应消息的签收状态[已签收]

        } else if (action == MsgActionEnum.KEEPALIVE.type) {
            // 2.4 心跳类型的消息
        }
        // clients.writeAndFlush(new TextWebSocketFrame("[Server receive msg at: " + LocalDateTime.now() + ",content: " + content + "]"));

    }

    /**
     * 当客户端连接到服务端之后（打开连接）
     * 获取客户端的channel，并且放到ChannelGroup中进行管理
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        clients.add(ctx.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        // 触发handlerRemoved时，ChannelGroup会自动移除对应客户端的channel
        clients.remove(ctx.channel());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        // 发生异常之后关闭连接（channel，随后从ChannelGroup移除
        ctx.channel().close();
        clients.remove(ctx.channel());
    }
}
