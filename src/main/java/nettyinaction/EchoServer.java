package nettyinaction;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import netty.server.handler.HeartBeatHandler;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

/**
 * @param: none
 * @description:
 * @author: KingJ
 * @create: 2019-07-11 22:40
 **/
public class EchoServer {
    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public void start() throws Exception {
        final EchoServerHandler serverHandler = new EchoServerHandler();
        EventLoopGroup group = new NioEventLoopGroup();

        try {
            // 启动引导
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(group)
                    .channel(NioServerSocketChannel.class)                      // 指定NIO传输channel
                    .localAddress(new InetSocketAddress(port))                  // 使用指定端口设置套接字地址
                    .childHandler(new ChannelInitializer<NioSocketChannel>() {     // 添加一个EchoServerHandler到子Channel的Channel Pipeline
                        protected void initChannel(NioSocketChannel socketChannel) throws Exception {      // 添加一个EchoServerHandler到子Channel的ChannelPipeline
                            socketChannel.pipeline().addLast(serverHandler);
                            socketChannel.pipeline().addLast(new IdleStateHandler(0, 0, 60, TimeUnit.SECONDS));
                            socketChannel.pipeline().addLast(new HeartBeatHandler());
                        }
                    });
            ChannelFuture f = bootstrap.bind().sync();                          // 异步绑定服务器；调用sync()方法阻塞等待知道绑定完成
            f.channel().closeFuture().sync();                                   // 获取Channel的CloseFuture，并阻塞当前线程直到他完成
        } finally {
            group.shutdownGracefully();                                         // 关闭EventGroupLoop，释放所有资源
        }
    }

    public static void main(String[] args) throws Exception {
        int port = 8083;
        new nettyinaction.EchoServer(port).start();
    }
}
