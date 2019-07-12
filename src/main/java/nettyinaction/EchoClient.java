package nettyinaction;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.Inet4Address;
import java.net.InetSocketAddress;

/**
 * @param: none
 * @description:
 * @author: KingJ
 * @create: 2019-07-12 22:43
 **/
public class EchoClient {
    private final int port;

    public EchoClient(int port) {
        this.port = port;
    }

    public void start() throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioServerSocketChannel.class)
                    .remoteAddress(new InetSocketAddress(Inet4Address.getLocalHost().getHostAddress(), port))
                    .handler(new ChannelInitializer<NioServerSocketChannel>() {
                        @Override
                        protected void initChannel(NioServerSocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline()
                                    .addLast(new EchoClientHandler());
                        }
                    });
            ChannelFuture f = bootstrap.connect().sync();
            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        int port = 8083;
        new EchoClient(port).start();
    }}
