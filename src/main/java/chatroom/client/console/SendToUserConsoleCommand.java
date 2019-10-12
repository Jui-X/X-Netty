package chatroom.client.console;

import io.netty.channel.Channel;
import chatroom.protocol.request.MessageRequestPacket;

import java.util.Scanner;

/**
 * @param: none
 * @description:
 * @author: KingJ
 * @create: 2019-09-02 19:21
 **/
public class SendToUserConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner sc, Channel channel) {
        System.out.print("send to user ");

        String userName = sc.next();
        String message = sc.next();

        channel.writeAndFlush(new MessageRequestPacket(userName, message));
    }
}
