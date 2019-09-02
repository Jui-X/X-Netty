package netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import netty.client.console.ConsoleCommandManager;
import netty.client.console.LoginConsoleCommand;
import netty.protocol.request.LoginRequestPacket;
import netty.protocol.request.MessageRequestPacket;
import netty.util.SessionUtil;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @param: none
 * @description:
 * @author: KingJ
 * @create: 2019-08-26 19:56
 **/
public class XClient {
    private static final int MAX_RETRY = 5;

    public static void main(String[] args) {
        NioEventLoopGroup worker =  new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(worker)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ClientInitializer());

            ChannelFuture future = connect(bootstrap, "localhost", 8084, MAX_RETRY);
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            worker.shutdownGracefully();
        }
    }

    private static ChannelFuture connect(Bootstrap bootstrap, String host, int port, int retry) {
        ChannelFuture channelFuture = bootstrap.connect(host, port)
                .addListener(future -> {
                    if (future.isSuccess()) {
                        System.out.println("Connect succeed");
                        Channel channel = ((ChannelFuture) future).channel();
                        startConsoleThread(channel);
                    } else if (retry == 0) {
                        System.out.println("Reconnect too much");
                    } else {
                        int order = (MAX_RETRY - retry) + 1;
                        int delay = 1 << order;
                        // group()方法返回一开始设置的worker group
                        // worker group.schedule()可以执行定时任务
                        bootstrap.config().group().schedule(() -> connect(bootstrap, host, port, retry - 1),
                                delay, TimeUnit.SECONDS);
                    }
                });

        return channelFuture;
    }

    private static void startConsoleThread(Channel channel) {
        Scanner sc = new Scanner(System.in);
        ConsoleCommandManager consoleCommandManager = new ConsoleCommandManager();
        LoginConsoleCommand loginConsoleCommand = new LoginConsoleCommand();

        new Thread(() -> {
            while (!Thread.interrupted()) {
                if (!SessionUtil.isLogin(channel)) {
                    loginConsoleCommand.exec(sc, channel);
                } else {
                    consoleCommandManager.exec(sc, channel);
                }
            }
        }).start();
    }
}
