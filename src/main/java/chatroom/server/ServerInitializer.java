package chatroom.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import chatroom.codec.PacketCodeCHandler;
import chatroom.codec.Spliter;
import chatroom.server.handler.*;

/**
 * @param: none
 * @description: 初始化器
 *               channel注册以后，会执行当中相应的初始化方法，逐个添加handler
 * @author: KingJ
 * @create: 2019-07-06 16:20
 **/
public class ServerInitializer extends ChannelInitializer<NioSocketChannel> {

    protected void initChannel(NioSocketChannel channel) throws Exception {
        // 通过SocketChannel去获得对应的pipeline管道
        ChannelPipeline pipeline = channel.pipeline();

        // 通过管道pipeline添加handler
        // HttpServerCodec是由Netty自己提供的助手类，可理解为拦截器
        // 当请求到服务端时做解码，到客户端时做编码
        // pipeline.addLast("HttpServerCodec", new HttpServerCodec());
        // 添加自定义的助手类，返回"Hello Netty~"
        // pipeline.addLast("customHandler", new CustomHandler());

        // 基于自定义协议的长度域拆包器，长度域偏移量为7，需要读取的长度域长度为4个字节
        // 自定义协议的包结构参见Packet类
        pipeline.addLast(new Spliter());
        pipeline.addLast(PacketCodeCHandler.INSTANCE);
        pipeline.addLast(LoginRequestHandler.INSTANCE);
        pipeline.addLast(AuthHandler.INSTANCE);
        pipeline.addLast(IMHandler.INSTANCE);
        pipeline.addLast(new IdleStateHandler(30, 50, 70));
        pipeline.addLast(new HeartBeatHandler());
    }
}
