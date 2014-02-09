package pl.com.zagorski.msg.bean;

import pl.com.zagorski.msg.data.UserManager;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * User: luke
 */
public class RegisterBean implements Serializable {

    UserManager userManager;

    @Size(min = 1, max = 10, message = "{messagedto.nickname.minmax}")
    @Pattern(regexp = "^[0-9a-zA-ZĄąĘęÓóĆćŁłŃńŚśŹźŻż]+$", message = "{plaintext}")
    private String username;
    @Size(min = 5, message = "{register.password.min}")
    private String password;
    @Size(min = 5, message = "{register.password.min}")
    private String confirmPassword;

    public RegisterBean() {
    }

    public RegisterBean(UserManager userManager) {
        this.userManager = userManager;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @AssertTrue(message = "{register.password.match}")
    public boolean isPasswordsEquals() {
        return password.equals(confirmPassword);
    }


    @AssertTrue(message = "{register.user.exists}")
    public boolean isUserExists() {

        return null == userManager.retrieveUser(username);
    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("RegisterBean");
        sb.append("{userManager=").append(userManager);
        sb.append(", username='").append(username).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", confirmPassword='").append(confirmPassword).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
