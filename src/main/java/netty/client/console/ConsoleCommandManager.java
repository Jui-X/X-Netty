package netty.client.console;

import io.netty.channel.Channel;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @param: none
 * @description:
 * @author: KingJ
 * @create: 2019-09-02 19:19
 **/
public class ConsoleCommandManager implements ConsoleCommand {
    private Map<String, ConsoleCommand> consoleCommandMap;

    public ConsoleCommandManager() {
        consoleCommandMap = new HashMap<>();
        consoleCommandMap.put("sendToUser", new SendToUserConsoleCommand());
        consoleCommandMap.put("logout", new LogoutConsoleCommand());
        consoleCommandMap.put("create", new CreateGroupConsoleCommand());
        consoleCommandMap.put("join", new JoinGroupConsoleCommand());
        consoleCommandMap.put("quit", new QuitGroupConsoleCommand());
        consoleCommandMap.put("list", new ListGroupMemberConsoleCommand());
    }

    @Override
    public void exec(Scanner sc, Channel channel) {
        String command = sc.next();

        ConsoleCommand consoleCommand = consoleCommandMap.get(command);
        if (consoleCommand != null) {
            consoleCommand.exec(sc, channel);
        } else {
            System.out.println("input wrong command!");
        }
    }
}
