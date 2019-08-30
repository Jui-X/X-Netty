package netty.protocol.response;

import lombok.Data;
import netty.enums.CommandEnum;
import netty.protocol.Packet;

/**
 * @param: none
 * @description:
 * @author: KingJ
 * @create: 2019-08-28 21:08
 **/
@Data
public class MessageResponsePacket extends Packet {
    private String fromUserId;
    private String fromUserName;
    private String message;

    @Override
    public Byte getCommand() {
        return CommandEnum.MESSAGE_RESPONSE.getValue();
    }
}
