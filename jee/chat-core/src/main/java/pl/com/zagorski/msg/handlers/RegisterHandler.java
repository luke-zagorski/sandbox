package pl.com.zagorski.msg.handlers;

import pl.com.zagorski.msg.bean.RegisterBean;
import pl.com.zagorski.msg.data.UserManager;
import pl.com.zagorski.msg.domain.User;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * User: luke
 */
@Model
public class RegisterHandler {

    public static final String ROLE = "user";
    public static final String SUCCESS = "success";
    @Inject
    UserManager userManager;

    private RegisterBean registerDto;

    @Named
    @Produces
    public RegisterBean getRegisterDto() {
        return registerDto;
    }

    public String register() {

        userManager.registerUser(convert(registerDto));
        return SUCCESS;
    }

    @PostConstruct
    void initialize() {
        registerDto = new RegisterBean(userManager);
    }

    User convert(RegisterBean registerBean) {

        User user = new User();
        user.setNickname(registerDto.getUsername());
        user.setPassword(registerDto.getPassword());
        user.setRole(ROLE);

        return user;
    }

}
