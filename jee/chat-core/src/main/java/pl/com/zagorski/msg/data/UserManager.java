package pl.com.zagorski.msg.data;

import pl.com.zagorski.msg.bean.UserBean;
import pl.com.zagorski.msg.domain.User;

/**
 * User: luke
 */
public interface UserManager {

    void registerUser(User user);

    UserBean retrieveUser(String nickname);

    void updateStatusMessage(String nickname, String statusMessage);
}
