package netty.protocol.request;

import netty.enums.CommandEnum;
import netty.protocol.Packet;

/**
 * @param: none
 * @description:
 * @author: KingJ
 * @create: 2019-09-02 20:31
 **/
public class LogoutRequestPacket extends Packet {
    @Override
    public Byte getCommand() {
        return CommandEnum.LOGOUT_REQUEST.getValue();
    }
}
