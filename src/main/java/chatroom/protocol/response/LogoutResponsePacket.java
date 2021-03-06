package chatroom.protocol.response;

import lombok.Data;
import chatroom.enums.CommandEnum;
import chatroom.protocol.Packet;

/**
 * @param: none
 * @description:
 * @author: KingJ
 * @create: 2019-09-02 20:32
 **/
@Data
public class LogoutResponsePacket extends Packet {
    private boolean success;
    private String reason;

    @Override
    public Byte getCommand() {
        return CommandEnum.LOGOUT_RESPONSE.getValue();
    }
}
