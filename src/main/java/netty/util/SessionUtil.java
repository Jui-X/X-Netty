package netty.util;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
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
    private static final Map<String, ChannelGroup> groupChannelGroupMap = new ConcurrentHashMap<>();

    public static void bindSession(Session session, Channel channel) {
        userChannelMap.put(session.getUserName(), channel);
        channel.attr(Attributes.SESSION).set(session);
    }

    public static void unBindSession(Channel channel) {
        if (isLogin(channel)) {
            userChannelMap.remove(getSession(channel).getUserName());
            channel.attr(Attributes.SESSION).set(null);
        }
    }

    public static boolean isLogin(Channel channel) {
        return channel.hasAttr(Attributes.SESSION);
    }

    public static Session getSession(Channel channel) {
        return channel.attr(Attributes.SESSION).get();
    }

    public static Channel getChannel(String userName) {
        return userChannelMap.get(userName);
    }

    public static void bindChannelGroup(String groupName, ChannelGroup channels) {
        groupChannelGroupMap.put(groupName, channels);
    }

    public static ChannelGroup getChannelGroup(String groupName) {
        return groupChannelGroupMap.get(groupName);
    }
}
