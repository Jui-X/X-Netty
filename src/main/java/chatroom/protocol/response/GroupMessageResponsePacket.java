package chatroom.protocol.response;

import lombok.Data;
import chatroom.enums.CommandEnum;
import chatroom.protocol.Packet;

/**
 * @param: none
 * @description:
 * @author: KingJ
 * @create: 2019-09-06 11:00
 **/
@Data
public class GroupMessageResponsePacket extends Packet {
    private String fromGroupName;
    private String fromUser;
    private String message;

    @Override
    public Byte getCommand() {
        return CommandEnum.MESSAGE_TO_GROUP_RESPONSE.getValue();
    }
}
