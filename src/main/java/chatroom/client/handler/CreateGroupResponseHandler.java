package chatroom.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import chatroom.protocol.response.CreateGroupResponsePacket;

/**
 * @param: none
 * @description:
 * @author: KingJ
 * @create: 2019-09-02 20:07
 **/
public class CreateGroupResponseHandler extends SimpleChannelInboundHandler<CreateGroupResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupResponsePacket createGroupResponsePacket) throws Exception {

        System.out.println("group create successfully, id: " + createGroupResponsePacket.getGroupId());
        System.out.println("group members contains: " + createGroupResponsePacket.getUserList());
    }
}
