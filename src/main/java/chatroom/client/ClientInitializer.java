package chatroom.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import chatroom.client.handler.*;
import chatroom.codec.PacketDecoder;
import chatroom.codec.PacketEncoder;
import chatroom.codec.Spliter;

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
        pipeline.addLast(new GroupMessageResponseHandler());
        pipeline.addLast(new UploadFileResponseHandler());
        pipeline.addLast(new LogoutResponseHandler());
        pipeline.addLast(new PacketEncoder());
    }
}
