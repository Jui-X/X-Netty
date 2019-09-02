package netty.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import netty.client.handler.*;
import netty.codec.PacketDecoder;
import netty.codec.PacketEncoder;
import netty.codec.Spliter;

/**
 * @param: none
 * @description:
 * @author: KingJ
 * @create: 2019-08-26 19:57
 **/
public class ClientInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        // pipeline.addLast("HttpClient", new HttpClientCodec());

        pipeline.addLast(new Spliter());
        pipeline.addLast(new PacketDecoder());
        pipeline.addLast(new LoginResponseHandler());
        pipeline.addLast(new MessageResponseHandler());
        pipeline.addLast(new CreateGroupResponseHandler());
        pipeline.addLast(new JoinGroupResponseHandler());
        pipeline.addLast(new QuitGroupResponseHandler());
        pipeline.addLast(new ListGroupMemberResponseHandler());
        pipeline.addLast(new LogoutResponseHandler());
        pipeline.addLast(new PacketEncoder());
    }
}
