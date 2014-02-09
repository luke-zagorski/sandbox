package pl.com.zagorski.msg.data;

import pl.com.zagorski.msg.bean.UserBean;
import pl.com.zagorski.msg.data.eao.UserEao;
import pl.com.zagorski.msg.domain.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.logging.Logger;

/**
 * User: luke
 */
@Stateless
public class UserManagerBasicImpl implements UserManager, Serializable {

    @Inject
    Logger logger;

    @Inject
    private UserEao userEao;

    @Override
    public void registerUser(User user) {

        StringBuilder sb = new StringBuilder();
        sb.append("\n[USER] Registering user: [").append(user).append("]");

        User dbUser = userEao.find(user.getNickname());
        if (dbUser == null) {

            sb.append("\n[MESSAGE] New User:[").append(user).append("]");
            logger.info(sb.toString());
            userEao.save(user);
        }else{
            throw new RuntimeException("Username already exists! [" + user.getNickname() + "]");
        }
    }

    @Override
    public UserBean retrieveUser(String nickname) {

        User user = userEao.find(nickname);

        if (user == null) {
            return null;
        }
        return new UserBean(user.getId(), user.getNickname(), user.getStatusMessage());
    }

    @Override
    public void updateStatusMessage(String nickname, String statusMessage) {

        User user = userEao.find(nickname);
        user.setStatusMessage(statusMessage);
    }
}
