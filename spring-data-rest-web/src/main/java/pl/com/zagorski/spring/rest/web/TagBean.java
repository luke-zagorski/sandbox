package pl.com.zagorski.spring.rest.web;

import com.google.gson.Gson;
import org.springframework.web.client.RestTemplate;
import pl.com.zagorski.spring.rest.web.model.Response;
import pl.com.zagorski.spring.rest.web.model.Tag;

import javax.faces.bean.ManagedBean;
import java.util.List;

/**
 * User: luke
 */
@ManagedBean
public class TagBean extends AbstractBean {

    private List<Tag> resultTag;

    public List<Tag> tagSuggestion(String query) {

        RestTemplate restTemplate = new RestTemplate();

        String result = restTemplate.getForObject(API_ADDRESS + "tag", String.class);

        Gson gson = new Gson();
        Response response = gson.fromJson(result, Response.class);

        return response.getContent();
    }

    public List<Tag> getResultTag() {
        return resultTag;
    }

    public void setResultTag(List<Tag> resultTag) {
        this.resultTag = resultTag;
    }
}
