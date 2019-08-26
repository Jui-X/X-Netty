package netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

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

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(worker)
                .channel(NioSocketChannel.class)
                .handler(new ClientInitializer());

        connect(bootstrap, "localhost", 8084, MAX_RETRY);
    }

    private static void connect(Bootstrap bootstrap, String host, int port, int retry) {
        bootstrap.connect(host, 8081)
                .addListener(future -> {
                    if (future.isSuccess()) {
                        System.out.println("Connect succeed");
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
    }
}
