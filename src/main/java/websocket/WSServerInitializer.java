package websocket;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * @param: none
 * @description:
 * @author: KingJ
 * @create: 2019-07-12 13:38
 **/
public class WSServerInitializer extends ChannelInitializer<SocketChannel> {
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();

        // websocket基于http协议，所以要有http编解码器
        pipeline.addLast(new HttpServerCodec());
        // 对写大数据流的支持
        pipeline.addLast(new ChunkedWriteHandler());
        // 对httpMessage进行聚合，聚合成HTTPFullRequest或者HTTPFullResponse
        // 在netty编程中几乎都会使用到此Handler
        pipeline.addLast(new HttpObjectAggregator(1024 * 64));

        /**
         * websocket服务器处理的协议，用于指定给客户端连接访问的路由 ： /ws
         * 此handler会帮你处理握手动作，close、ping、pong（ping+pong=心跳
         * 对于websocket来讲，都是以frames进行传输的，不同的数据类型对应不同的frame
         */
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));

        // 自定义handler
        pipeline.addLast(new ChatHandler());

        pipeline.addLast(new IdleStateHandler(3, 5, 8));

        // 自定义的空闲状态检测Handler
        pipeline.addLast(new HeartBeatHandler());

    }
}
