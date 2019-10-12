package chatroom.attribute;

import io.netty.util.AttributeKey;
import chatroom.session.Session;

public interface Attributes {
    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");
}
