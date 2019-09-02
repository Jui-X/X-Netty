package netty.client.console;

import io.netty.channel.Channel;
import netty.protocol.request.QuitGroupRequestPacket;

import java.util.Scanner;

/**
 * @param: none
 * @description:
 * @author: KingJ
 * @create: 2019-09-02 21:11
 **/
public class QuitGroupConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner sc, Channel channel) {
        QuitGroupRequestPacket quitGroupRequestPacket = new QuitGroupRequestPacket();

        System.out.print("plz input group name to quit: ");
        String groupName = sc.next();

        quitGroupRequestPacket.setGroupName(groupName);

        channel.writeAndFlush(quitGroupRequestPacket);
    }
}
