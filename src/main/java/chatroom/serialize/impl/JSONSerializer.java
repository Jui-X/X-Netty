package chatroom.serialize.impl;

import com.alibaba.fastjson.JSON;
import chatroom.enums.SerializeEnum;
import chatroom.serialize.Serializer;

/**
 * @param: none
 * @description:
 * @author: KingJ
 * @create: 2019-08-26 21:42
 **/
public class JSONSerializer implements Serializer {
    @Override
    public Byte serializeAlgorithm() {
        return SerializeEnum.JSON.getValue();
    }

    @Override
    public byte[] serialize(Object obj) {
        return JSON.toJSONBytes(obj);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes, clazz);
    }
}
