package netty.enums;

import lombok.Getter;

@Getter
public enum CommandEnum {
    LOGIN_REQUEST("LOGIN_REQUEST", (byte) 1),
    LOGIN_RESPONSE("LOGIN_RESPONSE", (byte) 2),
    MESSAGE_REQUEST("MESSAGE_REQUEST", (byte) 3),
    MESSAGE_RESPONSE("MESSAGE_RESPONSE", (byte) 4),
    CREATE_GROUP_REQUEST("CREATE_GROUP_REQUEST", (byte) 5),
    CREATE_GROUP_RESPONSE("CREATE_GROUP_RESPONSE", (byte) 6),
    LOGOUT_REQUEST("LOGOUT_REQUEST", (byte) 7),
    LOGOUT_RESPONSE("LOGOUT_RESPONSE", (byte) 8),
    JOIN_GROUP_REQUEST("JOIN_GROUP_REQUEST", (byte) 9),
    JOIN_GROUP_RESPONSE("JOIN_GROUP_RESPONSE", (byte) 10),
    QUIT_GROUP_REQUEST("QUIT_GROUP_REQUEST", (byte) 11),
    QUIT_GROUP_RESPONSE("QUIT_GROUP_RESPONSE", (byte) 12),
    LIST_GROUP_MEMBER_REQUEST("LIST_GROUP_MEMBER_REQUEST", (byte) 13),
    LIST_GROUP_MEMBER_RESPONSE("LIST_GROUP_MEMBER_RESPONSE", (byte) 14),
    MESSAGE_TO_GROUP_REQUEST("MESSAGE_TO_GROUP_REQUEST", (byte) 15),
    MESSAGE_TO_GROUP_RESPONSE("MESSAGE_TO_GROUP_RESPONSE", (byte) 16),
    ;

    private String type;
    private byte value;

    CommandEnum(String type, byte value) {
        this.type = type;
        this.value = value;
    }
}
