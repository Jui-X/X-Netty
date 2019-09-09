package netty.server.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import netty.protocol.request.UploadFileRequestPacket;
import netty.protocol.response.UploadFileResponsePacket;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

/**
 * @param: none
 * @description:
 * @author: KingJ
 * @create: 2019-09-09 16:24
 **/
@ChannelHandler.Sharable
public class UploadFileRequestHandler extends SimpleChannelInboundHandler<UploadFileRequestPacket> {
    public static final UploadFileRequestHandler INSTANCE = new UploadFileRequestHandler();
    private static final String FILE_DIR = "/Users/hongweihu/Documents/GitHub/X-Netty/src/resources";

    private UploadFileRequestHandler() {

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, UploadFileRequestPacket uploadFileRequestPacket) throws Exception {
        File file = uploadFileRequestPacket.getFile();

        if (!file.exists()) {
            System.out.println("No Such a File: " + uploadFileRequestPacket.getFileName());
            throw new FileNotFoundException();
        }

        byte[] bytes = uploadFileRequestPacket.getBytes();
        int byteRead = uploadFileRequestPacket.getEndPos();
        String fileName = uploadFileRequestPacket.getFileName();
        String path = FILE_DIR + File.separator + fileName;

        File newFile = new File(path);
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(newFile, "rw")) {
            randomAccessFile.seek(0);
            randomAccessFile.write(bytes);
        }

        UploadFileResponsePacket uploadFileResponsePacket = new UploadFileResponsePacket();
        if (byteRead > 0) {
            uploadFileResponsePacket.setMsg("Server receive file: " + uploadFileRequestPacket.getFileName() + "!");
            uploadFileResponsePacket.setByteRead(byteRead);
        }

        ctx.channel().writeAndFlush(uploadFileResponsePacket);

    }
}
