package netty.util;

import io.netty.channel.Channel;
import netty.attribute.Attributes;
import netty.session.Session;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @param: none
 * @description:
 * @author: KingJ
 * @create: 2019-08-28 21:17
 **/
public class SessionUtil {
    private static final Map<String, Channel> userChannelMap = new ConcurrentHashMap<>();

    public static void bindSession(Session session, Channel channel) {
        userChannelMap.put(session.getUserId(), channel);
        channel.attr(Attributes.SESSION).set(session);
    }

    public static void unBindSession(Channel channel) {
        if (isLogin(channel)) {
            userChannelMap.remove(getSession(channel).getUserId());
            channel.attr(Attributes.SESSION).set(null);
        }
    }

    public static boolean isLogin(Channel channel) {
        return channel.hasAttr(Attributes.SESSION);
    }

    public static Session getSession(Channel channel) {
        return channel.attr(Attributes.SESSION).get();
    }

    public static Channel getChannel(String userId) {
        return userChannelMap.get(userId);
    }
}
