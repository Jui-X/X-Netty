package chatroom.protocol.response;

import lombok.Data;
import chatroom.enums.CommandEnum;
import chatroom.protocol.Packet;

/**
 * @param: none
 * @description:
 * @author: KingJ
 * @create: 2019-09-02 21:09
 **/
@Data
public class QuitGroupResponsePacket extends Packet {
    private String groupName;
    private boolean success;
    private String reason;

    @Override
    public Byte getCommand() {
        return CommandEnum.QUIT_GROUP_RESPONSE.getValue();
    }
}
