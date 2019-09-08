package netty.protocol.response;

import netty.enums.CommandEnum;
import netty.protocol.Packet;

/**
 * @param: none
 * @description:
 * @author: KingJ
 * @create: 2019-09-07 12:10
 **/
public class UploadFileResponsePacket extends Packet {


    @Override
    public Byte getCommand() {
        return CommandEnum.UPLOAD_FILE_RESPONSE.getValue();
    }
}
