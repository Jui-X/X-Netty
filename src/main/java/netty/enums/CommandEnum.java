package netty.enums;

import lombok.Getter;

@Getter
public enum CommandEnum {
    LOGIN_REQUEST("LOGIN_REQUEST", (byte) 1),
    LOGIN_RESPONSE("LOGIN_RESPONSE", (byte) 2),
    ;

    private String type;
    private byte value;

    CommandEnum(String type, byte value) {
        this.type = type;
        this.value = value;
    }
}
