package chatroom.server.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import chatroom.enums.CommandEnum;
import chatroom.protocol.Packet;

import java.util.HashMap;
import java.util.Map;

/**
 * @param: none
 * @description:
 * @author: KingJ
 * @create: 2019-09-07 10:42
 **/
@ChannelHandler.Sharable
public class IMHandler extends SimpleChannelInboundHandler<Packet> {
    public static final IMHandler INSTANCE = new IMHandler();

    private Map<Byte, SimpleChannelInboundHandler<? extends Packet>> handlerMap;

    private IMHandler() {
        handlerMap = new HashMap<>();

        handlerMap.put(CommandEnum.LOGIN_REQUEST.getValue(), MessageRequestHandler.INSTANCE);
        handlerMap.put(CommandEnum.MESSAGE_REQUEST.getValue(), MessageRequestHandler.INSTANCE);
        handlerMap.put(CommandEnum.CREATE_GROUP_REQUEST.getValue(), CreateGroupRequestHandler.INSTANCE);
        handlerMap.put(CommandEnum.LOGOUT_REQUEST.getValue(), LogoutRequestHandler.INSTANCE);
        handlerMap.put(CommandEnum.JOIN_GROUP_REQUEST.getValue(), JoinGroupRequestHandler.INSTANCE);
        handlerMap.put(CommandEnum.QUIT_GROUP_REQUEST.getValue(), QuitGroupRequestHandler.INSTANCE);
        handlerMap.put(CommandEnum.LIST_GROUP_MEMBER_REQUEST.getValue(), ListGroupMemberRequestHandler.INSTANCE);
        handlerMap.put(CommandEnum.MESSAGE_TO_GROUP_REQUEST.getValue(), GroupMessageRequestHandler.INSTANCE);
        handlerMap.put(CommandEnum.UPLOAD_FILE_REQUEST.getValue(), UploadFileRequestHandler.INSTANCE);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Packet packet) throws Exception {
        handlerMap.get(packet.getCommand()).channelRead(ctx, packet);
    }
}
