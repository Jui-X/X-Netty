package chatroom.protocol;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @param: none
 * @description:
 * @author: KingJ
 * @create: 2019-08-26 21:24
 **/
@Data
public abstract class Packet {
    /** 版本 */
    @JSONField(deserialize = false, serialize = false)
    private int version = 1;

    /**
     * 获取指令
     **/
    @JSONField(deserialize = false, serialize = false)
    public abstract Byte getCommand();
}
