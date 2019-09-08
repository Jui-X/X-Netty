package netty.client.console;

import io.netty.channel.Channel;
import netty.protocol.request.UploadFileRequestPacket;

import java.io.File;
import java.util.Scanner;

/**
 * @param: none
 * @description:
 * @author: KingJ
 * @create: 2019-09-07 11:58
 **/
public class UploadFileConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner sc, Channel channel) {
        UploadFileRequestPacket uploadFileRequestPacket = new UploadFileRequestPacket();

        System.out.println("plz input the file name: ");
        String fileName = sc.next();
        File file = new File(fileName);

        uploadFileRequestPacket.setFile(file);
        uploadFileRequestPacket.setFileName(fileName);
        uploadFileRequestPacket.setStartPos(0);

        channel.writeAndFlush(uploadFileRequestPacket);
    }
}
