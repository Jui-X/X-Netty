package netty.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import netty.enums.CommandEnum;
import netty.enums.SerializeEnum;
import netty.protocol.request.LoginRequestPacket;
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
    private static final int MAGIC_NUMBER = 0x12345678;
    public static final PacketCodeC INSTANCE = new PacketCodeC();

    private static final Map<Byte, Class<? extends Packet>> packetTypeMap;
    private static final Map<Byte, Serializer> serializerMap;

    static {
        // 存放 packet包的请求类型 和 适用的Packet类的实现的映射
        packetTypeMap = new HashMap<>();
        packetTypeMap.put(CommandEnum.LOGIN_REQUEST.getValue(), LoginRequestPacket.class);

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
    public ByteBuf encode(ByteBufAllocator allocator, Packet packet) {
        // 创建io相关的Buffer对象，尽可能返回直接内存对象
        ByteBuf byteBuf = allocator.ioBuffer();
        // 序列化Java对象
        byte[] bytes = Serializer.DEFAULT.serialize(packet);

        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(SerializeEnum.JSON.getValue());
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(bytes.length);
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
        byteBuf.readBytes(length);

        Class<? extends Packet> requestType = getRequestType(command);
        Serializer serializer = getSerializer(serializeAlgorithm);

        if (requestType != null && serializer != null) {
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
