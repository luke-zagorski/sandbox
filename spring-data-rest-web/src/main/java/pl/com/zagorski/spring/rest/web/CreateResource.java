package pl.com.zagorski.spring.rest.web;

import org.springframework.web.client.RestTemplate;

import javax.faces.bean.ManagedBean;

/**
 * User: luke
 */
@ManagedBean
public class CreateResource extends AbstractBean {

    private String note;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void processResource() {

        RestTemplate restTemplate = new RestTemplate();

        StringBuilder send = new StringBuilder();
        send.append("{\n");
        send.append("\"note\": \"");
        send.append(note);
        send.append("\"\n");
        send.append("\t\n}");

        restTemplate.postForObject(API_ADDRESS + "/memo", send.toString(), String.class);
    }
}
