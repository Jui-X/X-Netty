package websocket;

import lombok.Data;

import java.io.Serializable;

/**
 * @param: none
 * @description:
 * @author: KingJ
 * @create: 2019-07-12 17:10
 **/
@Data
public class DataContent implements Serializable {

    private static long serialVersionUID = -1437106833360695142L;

    // 动作类型
    private Integer action;
    // 用户聊天内容entity
    private ChatMsg msg;
    // 扩展字段
    private String extend;
}
