package netty.client.console;

import io.netty.channel.Channel;
import netty.protocol.request.GroupMessageRequestPacket;

import java.util.Scanner;

/**
 * @param: none
 * @description:
 * @author: KingJ
 * @create: 2019-09-06 10:55
 **/
public class SendToGroupConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner sc, Channel channel) {
        System.out.print("Send msg to group: ");
        String toGroup = sc.next();
        System.out.println("message: ");
        String message = sc.next();

        channel.writeAndFlush(new GroupMessageRequestPacket(toGroup, message));
    }
}
