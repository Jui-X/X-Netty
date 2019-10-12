package chatroom.protocol.response;

import lombok.Data;
import chatroom.enums.CommandEnum;
import chatroom.protocol.Packet;

/**
 * @param: none
 * @description:
 * @author: KingJ
 * @create: 2019-08-26 22:37
 **/
@Data
public class LoginResponsePacket extends Packet {
    private String userId;
    private String userName;
    private boolean success;
    private String reason;

    @Override
    public Byte getCommand() {
        return CommandEnum.LOGIN_RESPONSE.getValue();
    }
}
