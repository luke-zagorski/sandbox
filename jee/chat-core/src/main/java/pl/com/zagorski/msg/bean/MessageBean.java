package pl.com.zagorski.msg.bean;


import javax.validation.constraints.Size;
import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;

/**
 * User: luke
 */
@Dto
public class MessageBean implements Serializable {

    private Date createdAt;
    private String createdAtString;
    private String nickname;
    private String text;

    public MessageBean() {
    }

    public MessageBean(Date createdAt, String nickname, String text) {

        this.nickname = nickname;
        this.text = text;
        this.createdAt = createdAt;
        DateFormat format = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
        this.createdAtString = format.format(createdAt);
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Size(min = 1, message = "{messagedto.text.min}")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreatedAt() {

        return createdAt;
    }

    public String getCreatedAtString() {
        return createdAtString;
    }

    public void setCreatedAtString(String createdAtString) {
        this.createdAtString = createdAtString;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        DateFormat format = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
        this.createdAtString = format.format(createdAt);
    }

    @Override
    public String toString() {
        return "MessageBean{" +
                "createdAt=" + createdAt +
                ", nickname='" + nickname + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
