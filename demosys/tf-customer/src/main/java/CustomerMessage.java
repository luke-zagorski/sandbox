import java.io.Serializable;

/**
 * @author Luke Zagorski
 *         Date 02.06.2013
 */
public class CustomerMessage implements Serializable {

    private String message;

    public CustomerMessage() {
    }

    public CustomerMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Message{");
        sb.append("message='").append(message).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
