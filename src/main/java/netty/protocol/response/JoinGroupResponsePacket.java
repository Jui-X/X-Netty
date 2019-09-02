package netty.protocol.response;

import lombok.Data;
import netty.enums.CommandEnum;
import netty.protocol.Packet;

/**
 * @param: none
 * @description:
 * @author: KingJ
 * @create: 2019-09-02 20:47
 **/
@Data
public class JoinGroupResponsePacket extends Packet {
    private String groupName;
    private boolean success;
    private String reason;

    @Override
    public Byte getCommand() {
        return CommandEnum.JOIN_GROUP_RESPONSE.getValue();
    }
}
