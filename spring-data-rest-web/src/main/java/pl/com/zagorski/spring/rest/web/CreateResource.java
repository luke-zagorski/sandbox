package pl.com.zagorski.spring.rest.web;

import javax.faces.bean.ManagedBean;

/**
 * User: luke
 */
@ManagedBean
public class CreateResource {

    private String messaage;



    public String getMessaage() {
        return messaage;
    }

    public void setMessaage(String messaage) {
        this.messaage = messaage;
    }


    public void processResource() {
        System.out.print(messaage);
    }
}
