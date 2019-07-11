package netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;
import netty.CustomHandler;

/**
 * @param: none
 * @description: 初始化器
 *               channel注册以后，会执行当中相应的初始化方法，逐个添加handler
 * @author: KingJ
 * @create: 2019-07-06 16:20
 **/
public class XServerInitializer extends ChannelInitializer<SocketChannel> {

    protected void initChannel(SocketChannel channel) throws Exception {
        // 通过SocketChannel去获得对应的pipeline管道
        ChannelPipeline pipeline = channel.pipeline();

        // 通过管道pipeline添加handler
        // HttpServerCodec是由Netty自己提供的助手类，可理解为拦截器
        // 当请求到服务端时做解码，到客户端时做编码
        pipeline.addLast("HttpServerCodec", new HttpServerCodec());
        // 添加自定义的助手类，返回"Hello Netty~"
        pipeline.addLast("customHandler", new CustomHandler());
    }
}
