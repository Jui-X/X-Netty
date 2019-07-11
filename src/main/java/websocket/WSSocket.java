package websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;

/**
 * @param: none
 * @description:
 * @author: KingJ
 * @create: 2019-07-11 23:19
 **/
public class WSSocket {

    public static void main(String[] args) {

        EventLoopGroup mainGruop = new NioEventLoopGroup();
        EventLoopGroup subGroup = new NioEventLoopGroup();

        ServerBootstrap bootstrap = new ServerBootstrap();
    }
}
