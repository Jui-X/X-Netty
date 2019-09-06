package netty.protocol.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import netty.enums.CommandEnum;
import netty.protocol.Packet;

/**
 * @param: none
 * @description:
 * @author: KingJ
 * @create: 2019-09-06 10:57
 **/
@Data
@NoArgsConstructor
public class GroupMessageRequestPacket extends Packet {
    private String toGroup;
    private String message;

    public GroupMessageRequestPacket(String toGroup, String message) {
        this.toGroup = toGroup;
        this.message = message;
    }

    @Override
    public Byte getCommand() {
        return CommandEnum.MESSAGE_TO_GROUP_REQUEST.getValue();
    }
}
