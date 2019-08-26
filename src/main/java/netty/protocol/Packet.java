package netty.protocol;

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
    private int version = 1;

    /**
     * 获取指令
     **/
    public abstract Byte getCommand();
}
