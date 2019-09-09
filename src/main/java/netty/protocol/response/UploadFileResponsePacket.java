package netty.protocol.response;

import lombok.Data;
import netty.enums.CommandEnum;
import netty.protocol.Packet;

/**
 * @param: none
 * @description:
 * @author: KingJ
 * @create: 2019-09-07 12:10
 **/
@Data
public class UploadFileResponsePacket extends Packet {
    private String msg;
    private int byteRead;

    @Override
    public Byte getCommand() {
        return CommandEnum.UPLOAD_FILE_RESPONSE.getValue();
    }
}
