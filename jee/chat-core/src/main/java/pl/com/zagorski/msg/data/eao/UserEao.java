package pl.com.zagorski.msg.data.eao;

import pl.com.zagorski.msg.domain.User;

import java.util.List;

/**
 * User: luke
 */
public interface UserEao {

    void save(User user);

    User find(String nickname);

    List<User> getUsers();

}
