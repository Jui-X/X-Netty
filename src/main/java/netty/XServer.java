package netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @param: none
 * @description: 实现客户端发送请求
 *               服务端返回 "Hello Netty"
 * @author: KingJ
 * @create: 2019-07-04 23:26
 **/
public class XServer {

    public static void main(String[] args) throws InterruptedException {

        // 定义一对线程组
        // 主线程组，用于接收客户端连接，但不做处理
        EventLoopGroup fatherGroup = new NioEventLoopGroup();
        // 从线程组，主线程组交付任务给从线程组
        EventLoopGroup sonGroup = new NioEventLoopGroup();

        try {
            // Netty服务器的创建，ServerBootStrap是一个启动类，用于启动ServerChannel
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(fatherGroup, sonGroup)        // 设置主从线程组
                    .channel(NioServerSocketChannel.class)      // 设置NIO的双向通道
                    .childHandler(new XServerInitializer());    // 子处理器，用于处理sonGroup

            // 启动Server，设置8088为启动端口号，启动方式为同步
            ChannelFuture channelFuture = serverBootstrap.bind(8088).sync();

            // 监听关闭的channel，设置为同步方式
            channelFuture.channel().closeFuture().sync();
        } finally {
            fatherGroup.shutdownGracefully();
            sonGroup.shutdownGracefully();
        }
    }
}
