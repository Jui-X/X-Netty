package chatroom.client.console;

import io.netty.channel.Channel;
import chatroom.protocol.request.UploadFileRequestPacket;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

/**
 * @param: none
 * @description:
 * @author: KingJ
 * @create: 2019-09-07 11:58
 **/
public class UploadFileConsoleCommand implements ConsoleCommand {
    private RandomAccessFile randomAccessFile;

    @Override
    public void exec(Scanner sc, Channel channel) {
        UploadFileRequestPacket uploadFileRequestPacket = new UploadFileRequestPacket();
        int lastPos;
        int byteRead;

        System.out.println("plz input the file name: ");
        String fileName = sc.next();

        uploadFileRequestPacket.setFileName(fileName.substring(fileName.lastIndexOf("/") + 1));
        uploadFileRequestPacket.setStartPos(0);

        try {
            randomAccessFile = new RandomAccessFile(new File(fileName), "r");
            randomAccessFile.seek(0);
            lastPos = (int) randomAccessFile.length() * 10;
            byte[] bytes = new byte[lastPos];

            if ((byteRead = randomAccessFile.read(bytes)) != -1) {
                uploadFileRequestPacket.setBytes(bytes);
                uploadFileRequestPacket.setEndPos(byteRead);

                channel.writeAndFlush(uploadFileRequestPacket);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("No Such a File: " + uploadFileRequestPacket.getFileName());
        } catch (IOException i) {
            i.printStackTrace();
        }finally {
            try {
                randomAccessFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
