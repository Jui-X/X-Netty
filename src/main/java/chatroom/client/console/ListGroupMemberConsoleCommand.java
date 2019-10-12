package chatroom.client.console;

import io.netty.channel.Channel;
import chatroom.protocol.request.ListGroupMemberRequestPacket;

import java.util.Scanner;

/**
 * @param: none
 * @description:
 * @author: KingJ
 * @create: 2019-09-02 21:24
 **/
public class ListGroupMemberConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner sc, Channel channel) {
        ListGroupMemberRequestPacket listGroupMemberRequestPacket = new ListGroupMemberRequestPacket();

        System.out.print("input group name to get group member: ");
        String groupName = sc.next();

        listGroupMemberRequestPacket.setGroupName(groupName);

        channel.writeAndFlush(listGroupMemberRequestPacket);
    }
}
