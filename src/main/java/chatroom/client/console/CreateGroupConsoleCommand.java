package chatroom.client.console;

import chatroom.protocol.request.CreateGroupRequestPacket;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @param: none
 * @description:
 * @author: KingJ
 * @create: 2019-09-02 19:22
 **/
public class CreateGroupConsoleCommand implements ConsoleCommand {
    private static final String USER_SEPARATOR = ",";

    @Override
    public void exec(Scanner sc, io.netty.channel.Channel channel) {
        CreateGroupRequestPacket createGroupRequestPacket = new CreateGroupRequestPacket();

        System.out.print("plz input the group name: ");
        String groupName = sc.next();
        System.out.println("invite users to join the group, use , as separator");
        String users = sc.next();
        createGroupRequestPacket.setGroupName(groupName);
        createGroupRequestPacket.setUserList(Arrays.asList(users.split(USER_SEPARATOR)));

        channel.writeAndFlush(createGroupRequestPacket);
    }
}
