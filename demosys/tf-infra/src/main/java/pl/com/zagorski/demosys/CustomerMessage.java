package pl.com.zagorski.demosys;

import java.io.Serializable;

/**
 * @author Luke Zagorski
 *         Date 02.06.2013
 */
public class CustomerMessage implements Serializable {

    private Object message;

    public CustomerMessage() {
    }

    public CustomerMessage(Object message) {
        this.message = message;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
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
