package chatroom.serialize;

import chatroom.serialize.impl.JSONSerializer;

public interface Serializer {

    /** JSON序列化 */
    byte JSON_SERIALIZER = 1;

    Serializer DEFAULT = new JSONSerializer();

    /**
     * 序列化算法
     */
    Byte serializeAlgorithm();

    /**
     * Java对象转二进制数组
     */
    byte[] serialize(Object obj);

    /**
     * 二进制转Java对象
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes);
}
