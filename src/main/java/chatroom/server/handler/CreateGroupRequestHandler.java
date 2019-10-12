package chatroom.server.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import chatroom.protocol.request.CreateGroupRequestPacket;
import chatroom.protocol.response.CreateGroupResponsePacket;
import chatroom.util.IDUtil;
import chatroom.util.SessionUtil;

import java.util.List;

/**
 * @param: none
 * @description:
 * @author: KingJ
 * @create: 2019-09-02 19:46
 **/
@ChannelHandler.Sharable
public class CreateGroupRequestHandler extends SimpleChannelInboundHandler<CreateGroupRequestPacket> {
    public static final CreateGroupRequestHandler INSTANCE = new CreateGroupRequestHandler();

    private CreateGroupRequestHandler() {

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupRequestPacket createGroupRequestPacket) throws Exception {
        List<String> userList = createGroupRequestPacket.getUserList();

        // 创建一个分组集合
        ChannelGroup channels = new DefaultChannelGroup(ctx.executor());

        // 遍历选出加入群聊的用户对应的channel
        for (String user : userList) {
            Channel channel = SessionUtil.getChannel(user);
            if (channel != null) {
                channels.add(channel);
            }
        }

        // 创建群聊结果的响应
        CreateGroupResponsePacket createGroupResponsePacket = new CreateGroupResponsePacket();
        createGroupResponsePacket.setSuccess(true);
        createGroupResponsePacket.setGroupName(createGroupRequestPacket.getGroupName());
        createGroupResponsePacket.setGroupId(IDUtil.randomId());
        createGroupResponsePacket.setUserList(userList);

        // 将响应消息发回给所有群内用户
        channels.writeAndFlush(createGroupResponsePacket);

        System.out.println("group create successfully, id: " + createGroupResponsePacket.getGroupId());
        System.out.println("group members contains: " + createGroupResponsePacket.getUserList());

        SessionUtil.bindChannelGroup(createGroupRequestPacket.getGroupName(), channels);
    }
}
