package pl.com.zagorski.demosys;

import org.springframework.stereotype.Component;

/**
 * @author Luke Zagorski
 *         Date 02.06.2013
 */
@Component
public class BillingService {

    public void process(CustomerMessage content) {

        Object message = content.getMessage();
    }

}
