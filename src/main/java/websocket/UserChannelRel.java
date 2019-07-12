package websocket;

import io.netty.channel.Channel;

import java.util.HashMap;

/**
 * @param: none
 * @description: 用户ID和channel的关联关系
 * @author: KingJ
 * @create: 2019-07-12 17:58
 **/
public class UserChannelRel {

    private static HashMap<String, Channel> manager = new HashMap<>();

    public static void put(String senderID, Channel channel) {
        manager.put(senderID, channel);
    }

    public static Channel get(String senderID) {
        return manager.get(senderID);
    }
}
