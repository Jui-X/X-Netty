package netty.enums;

import lombok.Getter;

@Getter
public enum CommandEnum {
    LOGIN_REQUEST("LOGIN_REQUEST", (byte) 1),
    LOGIN_RESPONSE("LOGIN_RESPONSE", (byte) 2),
    MESSAGE_REQUEST("MESSAGE_REQUEST", (byte) 3),
    MESSAGE_RESPONSE("MESSAGE_RESPONSE", (byte) 4)
    ;

    private String type;
    private byte value;

    CommandEnum(String type, byte value) {
        this.type = type;
        this.value = value;
    }
}
