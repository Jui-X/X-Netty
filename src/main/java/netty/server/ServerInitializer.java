package netty.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.timeout.IdleStateHandler;
import netty.codec.PacketDecoder;
import netty.codec.PacketEncoder;
import netty.codec.Spliter;
import netty.server.handler.*;

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
        pipeline.addLast(new PacketDecoder());
        pipeline.addLast(new LoginRequestHandler());
        pipeline.addLast(new AuthHandler());
        pipeline.addLast(new MessageRequestHandler());
        pipeline.addLast(new CreateGroupRequestHandler());
        pipeline.addLast(new JoinGroupRequestHandler());
        pipeline.addLast(new QuitGroupRequestHandler());
        pipeline.addLast(new ListGroupMemberRequestHandler());
        pipeline.addLast(new LogoutRequestHandler());
        pipeline.addLast(new IdleStateHandler(30, 50, 70));
        pipeline.addLast(new HeartBeatHandler());
        pipeline.addLast(new PacketEncoder());
    }
}
