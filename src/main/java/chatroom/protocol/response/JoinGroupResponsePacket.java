package chatroom.protocol.response;

import lombok.Data;
import chatroom.enums.CommandEnum;
import chatroom.protocol.Packet;

import java.util.ArrayList;
import java.util.List;

/**
 * @param: none
 * @description:
 * @author: KingJ
 * @create: 2019-09-02 20:47
 **/
@Data
public class JoinGroupResponsePacket extends Packet {
    private String groupName;
    private List<String> userList = new ArrayList<>();
    private boolean success;
    private String reason;

    @Override
    public Byte getCommand() {
        return CommandEnum.JOIN_GROUP_RESPONSE.getValue();
    }
}
