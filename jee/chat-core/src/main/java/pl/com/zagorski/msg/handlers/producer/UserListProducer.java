package pl.com.zagorski.msg.handlers.producer;

import pl.com.zagorski.msg.bean.UserBean;
import pl.com.zagorski.msg.data.eao.UserEao;
import pl.com.zagorski.msg.domain.User;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * User: luke
 */
@RequestScoped
public class UserListProducer implements Serializable {

    @Inject
    UserEao userEao;

    List<UserBean> userBeanList;

    @Produces
    @Named("allUsersList")
    public List<UserBean> getUserBeanList() {
        return userBeanList;
    }

    @PostConstruct
    public void retrieveAllUsers() {

        userBeanList = new ArrayList<UserBean>();

        List<User> userList = userEao.getUsers();

        for (User user : userList) {
            userBeanList.add(new UserBean(user.getId(), user.getNickname(),user.getStatusMessage()));
        }
    }


}
