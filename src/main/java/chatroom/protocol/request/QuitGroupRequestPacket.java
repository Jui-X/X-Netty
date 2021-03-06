package chatroom.protocol.request;

import lombok.Data;
import chatroom.enums.CommandEnum;
import chatroom.protocol.Packet;

/**
 * @param: none
 * @description:
 * @author: KingJ
 * @create: 2019-09-02 21:08
 **/
@Data
public class QuitGroupRequestPacket extends Packet {
    private String groupName;

    @Override
    public Byte getCommand() {
        return CommandEnum.QUIT_GROUP_REQUEST.getValue();
    }
}
