package chatroom.enums;

import io.netty.util.AttributeKey;
import lombok.Getter;

/**
 * @param: none
 * @description:
 * @author: KingJ
 * @create: 2019-08-29 15:22
 **/
@Getter
public enum AttributesEnum {
    SESSION("session", AttributeKey.newInstance("session"))
    ;

    private String attr;
    private AttributeKey<?> attributeKey;

    AttributesEnum(String attr, AttributeKey<?> attributeKey) {
        this.attr = attr;
        this.attributeKey = attributeKey;
    }
}
