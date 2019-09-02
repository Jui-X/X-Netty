package netty.client.console;

import io.netty.channel.Channel;
import netty.protocol.request.LoginRequestPacket;

import java.util.Scanner;

/**
 * @param: none
 * @description:
 * @author: KingJ
 * @create: 2019-09-02 19:27
 **/
public class LoginConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner sc, Channel channel) {
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();

        System.out.print("plz input ur name: ");
        loginRequestPacket.setUserName(sc.nextLine());
        loginRequestPacket.setPassword("pwd");

        channel.writeAndFlush(loginRequestPacket);
        waitForLoginResponse();
    }

    private static void waitForLoginResponse() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {
        }
    }
}
