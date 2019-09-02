package netty.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import netty.enums.CommandEnum;
import netty.enums.SerializeEnum;
import netty.protocol.request.*;
import netty.protocol.response.*;
import netty.serialize.Serializer;
import netty.serialize.impl.JSONSerializer;

import java.util.HashMap;
import java.util.Map;

/**
 * @param: none
 * @description:
 * @author: KingJ
 * @create: 2019-08-26 21:54
 **/
public class PacketCodeC {
    public static final int MAGIC_NUMBER = 0x12345678;
    public static final PacketCodeC INSTANCE = new PacketCodeC();

    private static final Map<Byte, Class<? extends Packet>> packetTypeMap;
    private static final Map<Byte, Serializer> serializerMap;

    static {
        // 存放 packet包的请求类型 和 适用的Packet类的实现的映射
        packetTypeMap = new HashMap<>();
        packetTypeMap.put(CommandEnum.LOGIN_REQUEST.getValue(), LoginRequestPacket.class);
        packetTypeMap.put(CommandEnum.LOGIN_RESPONSE.getValue(), LoginResponsePacket.class);
        packetTypeMap.put(CommandEnum.MESSAGE_REQUEST.getValue(), MessageRequestPacket.class);
        packetTypeMap.put(CommandEnum.MESSAGE_RESPONSE.getValue(), MessageResponsePacket.class);
        packetTypeMap.put(CommandEnum.CREATE_GROUP_REQUEST.getValue(), CreateGroupRequestPacket.class);
        packetTypeMap.put(CommandEnum.CREATE_GROUP_RESPONSE.getValue(), CreateGroupResponsePacket.class);
        packetTypeMap.put(CommandEnum.LOGOUT_REQUEST.getValue(), LogoutRequestPacket.class);
        packetTypeMap.put(CommandEnum.LOGOUT_RESPONSE.getValue(), LogoutResponsePacket.class);
        packetTypeMap.put(CommandEnum.JOIN_GROUP_REQUEST.getValue(), JoinGroupRequestPacket.class);
        packetTypeMap.put(CommandEnum.JOIN_GROUP_RESPONSE.getValue(), JoinGroupResponsePacket.class);
        packetTypeMap.put(CommandEnum.QUIT_GROUP_REQUEST.getValue(), QuitGroupRequestPacket.class);
        packetTypeMap.put(CommandEnum.QUIT_GROUP_RESPONSE.getValue(), QuitGroupResponsePacket.class);
        packetTypeMap.put(CommandEnum.LIST_GROUP_MEMBER_REQUEST.getValue(), ListGroupMemberRequestPacket.class);
        packetTypeMap.put(CommandEnum.LIST_GROUP_MEMBER_RESPONSE.getValue(), ListGroupMemberResponsePacket.class);

        // 存放 序列化算法 和 序列化实现类的映射
        serializerMap = new HashMap<>();
        Serializer serializer = new JSONSerializer();
        serializerMap.put(SerializeEnum.JSON.getValue(), serializer);
    }

    /**
     * 编码
     * 将Java对象转二进制
     * @return
     */
    public ByteBuf encode(ByteBuf byteBuf, Packet packet) {
        // 序列化Java对象
        byte[] bytes = Serializer.DEFAULT.serialize(packet);

        // 实际编码过程
        // 头4个bit为Magic Number，用于检测请求包是否符合我们的自定义协议
        byteBuf.writeInt(MAGIC_NUMBER);
        // 1个bit表示当前版本号
        byteBuf.writeByte(packet.getVersion());
        // 1个bit表示序列化算法
        byteBuf.writeByte(SerializeEnum.JSON.getValue());
        // 1个bit表示指令
        byteBuf.writeByte(packet.getCommand());
        // 4个bit表示需要接收的数据长度
        byteBuf.writeInt(bytes.length);
        // 接下来的都是需要接收的数据二进制流
        byteBuf.writeBytes(bytes);

        return byteBuf;
    }

    public Packet decode(ByteBuf byteBuf) {
        // 跳过MAGIC_NUMBER
        byteBuf.skipBytes(4);
        // 跳过版本号
        byteBuf.skipBytes(1);
        // 序列化算法标识
        byte serializeAlgorithm = byteBuf.readByte();
        // command指令
        byte command = byteBuf.readByte();
        // 数据包长度
        int length = byteBuf.readInt();
        // 获取包内容
        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);

        // 根据command获取当前请求包的类型
        Class<? extends Packet> requestType = getRequestType(command);
        // 根据serializeAlgorithm获取当前序列化算法
        Serializer serializer = getSerializer(serializeAlgorithm);

        if (requestType != null && serializer != null) {
            // 根据请求包的类型和序列化算法获得请求包的Java对象
            return serializer.deserialize(requestType, bytes);
        }

        return null;
    }

    private Serializer getSerializer(byte serializeAlgorithm) {
        return serializerMap.get(serializeAlgorithm);
    }

    private Class<? extends Packet> getRequestType(byte command) {
        return packetTypeMap.get(command);
    }
}
