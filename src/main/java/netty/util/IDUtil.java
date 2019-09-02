package netty.util;

import java.util.UUID;

/**
 * @param: none
 * @description:
 * @author: KingJ
 * @create: 2019-09-02 19:32
 **/
public class IDUtil {
    public static String randomId() {
        return UUID.randomUUID().toString().split("-")[0];
    }
}
