package netty.protocol.request;

import lombok.Data;
import netty.enums.CommandEnum;
import netty.protocol.Packet;


/**
 * @param: none
 * @description:
 * @author: KingJ
 * @create: 2019-08-26 21:28
 **/
@Data
public class LoginRequestPacket extends Packet {
    private String userId;
    private String userName;
    private String password;

    @Override
    public Byte getCommand() {
        return CommandEnum.LOGIN_REQUEST.getValue();
    }
}
