package netty.protocol.request;

import lombok.Data;
import netty.enums.CommandEnum;
import netty.protocol.Packet;

import java.io.File;

/**
 * @param: none
 * @description:
 * @author: KingJ
 * @create: 2019-09-07 12:09
 **/
@Data
public class UploadFileRequestPacket extends Packet {
    private File file;
    private String fileName;
    private byte[] bytes;
    private int startPos;
    private int endPos;

    @Override
    public Byte getCommand() {
        return CommandEnum.UPLOAD_FILE_REQUEST.getValue();
    }
}
