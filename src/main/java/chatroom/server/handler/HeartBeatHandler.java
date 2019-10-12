package chatroom.server.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * @param: none
 * @description: 用于检测Channel心跳的handler
 *               继承自ChannelInboundHandlerAdapter，无需实现其他方法
 * @author: KingJ
 * @create: 2019-07-12 20:05
 **/
public class HeartBeatHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {

        // 判断evt是否时IdleStateEvent，用于触发用户事件，包含读/写/读写空闲
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;

            if (event.state() == IdleState.READER_IDLE) {
                // System.out.println("Server is READER_IDLE");
            } else if (event.state() == IdleState.WRITER_IDLE) {
                // System.out.println("Server is WRITER_IDLE");
            } else if (event.state() == IdleState.ALL_IDLE) {
                Channel channel = ctx.channel();
                // channel.close();
            }
        }

    }
}
