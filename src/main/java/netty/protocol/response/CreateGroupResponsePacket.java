package netty.protocol.response;

import lombok.Data;
import netty.enums.CommandEnum;
import netty.protocol.Packet;

import java.util.List;

/**
 * @param: none
 * @description:
 * @author: KingJ
 * @create: 2019-09-02 19:44
 **/
@Data
public class CreateGroupResponsePacket extends Packet {
    private boolean success;
    private String groupId;
    private String groupName;
    private List<String> userList;

    @Override
    public Byte getCommand() {
        return CommandEnum.CREATE_GROUP_RESPONSE.getValue();
    }
}
