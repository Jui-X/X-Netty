import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;

/**
 * @param: none
 * @description: 实现客户端发送请求
 *               服务端返回 "Hello Netty"
 * @author: KingJ
 * @create: 2019-07-04 23:26
 **/
public class XServer {

    public static void main(String[] args) {

        // 定义一对线程组
        // 主线程组，用于接收客户端连接，但不做处理
        EventLoopGroup fatherGroup = new NioEventLoopGroup();
        // 从线程组，主线程组交付任务给从线程组
        EventLoopGroup sonGroup = new NioEventLoopGroup();
    }
}
