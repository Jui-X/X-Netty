package chatroom.protocol.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import chatroom.enums.CommandEnum;
import chatroom.protocol.Packet;

/**
 * @param: none
 * @description:
 * @author: KingJ
 * @create: 2019-08-28 21:07
 **/
@Data
@NoArgsConstructor
public class MessageRequestPacket extends Packet {
    private String toUserName;
    private String message;

    public MessageRequestPacket(String toUserName, String message) {
        this.toUserName = toUserName;
        this.message = message;
    }

    @Override
    public Byte getCommand() {
        return CommandEnum.MESSAGE_REQUEST.getValue();
    }
}
