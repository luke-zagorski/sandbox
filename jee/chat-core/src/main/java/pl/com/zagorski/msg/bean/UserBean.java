package pl.com.zagorski.msg.bean;

import pl.com.zagorski.msg.data.enums.ContactStatusEnum;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * User: luke
 */
@Dto
public class UserBean implements Comparable<UserBean>, Serializable {

    private long id;

    private String nickname;
    private Integer status;
    private String statusMessage;


    public UserBean() {

    }

    public UserBean(String nickname) {
        this.nickname = nickname;
    }

    public UserBean(long id, String nickname) {
        this.id = id;
        this.nickname = nickname;
    }

    public UserBean(Long id, String nickname, String statusMessage) {
        this.id = id;
        this.nickname = nickname;
        this.statusMessage = statusMessage;
    }

    public UserBean(Long id, String nickname, String statusMessage, ContactStatusEnum status) {
        this.id = id;
        this.nickname = nickname;
        this.statusMessage = statusMessage;
        this.status = status.ordinal();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Size(min = 1, max = 10, message = "{messagedto.nickname.minmax}")
    @Pattern(regexp = "^[0-9a-zA-ZĄąĘęÓóĆćŁłŃńŚśŹźŻż]+$", message = "{plaintext}")
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(ContactStatusEnum status) {
        this.status = status.ordinal();
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserBean userBean = (UserBean) o;

        if (id != userBean.id) return false;
        if (nickname != null ? !nickname.equals(userBean.nickname) : userBean.nickname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (nickname != null ? nickname.hashCode() : 0);
        return result;
    }


    @Override
    public int compareTo(final UserBean userBean) {

        checkNotNull(userBean);

        if (this.getStatus() == null ^ userBean.getStatus() == null) {
            return (this.getStatus() == null) ? -1 : 1;
        }

        if (this.getStatus() == null && userBean.getStatus() == null) {
            return 0;
        }

        return this.status.compareTo(userBean.getStatus());
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("UserBean");
        sb.append("{id=").append(id);
        sb.append(", nickname='").append(nickname).append('\'');
        sb.append(", status=").append(status);
        sb.append(", statusMessage='").append(statusMessage).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
