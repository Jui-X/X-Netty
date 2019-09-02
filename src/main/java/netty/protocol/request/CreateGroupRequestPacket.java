package netty.protocol.request;

import lombok.Data;
import netty.enums.CommandEnum;
import netty.protocol.Packet;

import java.util.List;

/**
 * @param: none
 * @description:
 * @author: KingJ
 * @create: 2019-09-02 19:37
 **/
@Data
public class CreateGroupRequestPacket extends Packet {
    private String groupName;
    private List<String> userList;

    @Override
    public Byte getCommand() {
        return CommandEnum.CREATE_GROUP_REQUEST.getValue();
    }
}
