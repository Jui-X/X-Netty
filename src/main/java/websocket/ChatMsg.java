package websocket;

import lombok.Data;

import java.io.Serializable;

/**
 * @param: none
 * @description:
 * @author: KingJ
 * @create: 2019-07-12 17:13
 **/
@Data
public class ChatMsg implements Serializable {

    private static final long serialVersionUID = -1052417595134850463L;

    // 发送者ID
    private String senderID;
    // 接收者ID
    private String receiverID;
    // 聊天内容
    private String msg;
    // 用于消息签收
    private String msgID;
}
