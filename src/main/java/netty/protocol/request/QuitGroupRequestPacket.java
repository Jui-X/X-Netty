package netty.protocol.request;

import lombok.Data;
import netty.enums.CommandEnum;
import netty.protocol.Packet;

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
        return CommandEnum.JOIN_GROUP_REQUEST.getValue();
    }
}
