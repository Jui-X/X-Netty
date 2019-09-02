package netty.client.console;

import io.netty.channel.Channel;
import netty.protocol.request.JoinGroupRequestPacket;

import java.util.Scanner;

/**
 * @param: none
 * @description:
 * @author: KingJ
 * @create: 2019-09-02 20:49
 **/
public class JoinGroupConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner sc, Channel channel) {
        JoinGroupRequestPacket joinGroupRequestPacket = new JoinGroupRequestPacket();

        System.out.print("plz input group name to join: ");
        String groupName = sc.next();

        joinGroupRequestPacket.setGroupName(groupName);

        channel.writeAndFlush(joinGroupRequestPacket);
    }
}
