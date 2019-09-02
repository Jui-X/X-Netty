package netty.protocol.request;

import lombok.Data;
import netty.enums.CommandEnum;
import netty.protocol.Packet;

/**
 * @param: none
 * @description:
 * @author: KingJ
 * @create: 2019-09-02 21:25
 **/
@Data
public class ListGroupMemberRequestPacket extends Packet {
    private String groupName;

    @Override
    public Byte getCommand() {
        return CommandEnum.LIST_GROUP_MEMBER_REQUEST.getValue();
    }
}
