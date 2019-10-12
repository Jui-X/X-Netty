package chatroom.enums;

import lombok.Getter;

/**
 * @param: none
 * @description:
 * @author: KingJ
 * @create: 2019-08-26 21:59
 **/
@Getter
public enum SerializeEnum {
    JSON("SerializeAlgorithm_JSON", (byte) 1),
    ;

    private String key;
    private byte value;

    SerializeEnum(String key, byte value) {
        this.key = key;
        this.value = value;
    }
}
