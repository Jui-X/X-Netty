package netty.session;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @param: none
 * @description:
 * @author: KingJ
 * @create: 2019-08-29 15:14
 **/
@Data
@NoArgsConstructor
public class Session {
    private String userId;
    private String userName;

    public Session(String userId, String userName) {
        this.userName = userName;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return userId + ": " + userName;
    }
}
