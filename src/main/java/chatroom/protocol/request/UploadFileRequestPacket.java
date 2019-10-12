package chatroom.protocol.request;

import lombok.Data;
import chatroom.enums.CommandEnum;
import chatroom.protocol.Packet;

/**
 * @param: none
 * @description:
 * @author: KingJ
 * @create: 2019-09-07 12:09
 **/
@Data
public class UploadFileRequestPacket extends Packet {
    private String fileName;
    private byte[] bytes;
    private int startPos;
    private int endPos;

    @Override
    public Byte getCommand() {
        return CommandEnum.UPLOAD_FILE_REQUEST.getValue();
    }
}
