package netty.protocol.request;

import lombok.Data;
import netty.enums.CommandEnum;
import netty.protocol.Packet;

/**
 * @param: none
 * @description:
 * @author: KingJ
 * @create: 2019-09-02 20:45
 **/
@Data
public class JoinGroupRequestPacket extends Packet {
    private String groupName;

    @Override
    public Byte getCommand() {
        return CommandEnum.JOIN_GROUP_REQUEST.getValue();
    }
}
