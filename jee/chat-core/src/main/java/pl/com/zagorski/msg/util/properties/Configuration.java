package pl.com.zagorski.msg.util.properties;

import pl.com.zagorski.msg.util.MyLog;

import javax.ejb.Singleton;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * User: luke
 */
@Singleton
public class Configuration {

    @Inject
    @MyLog
    Logger logger;

    private Map<String, Properties> propertiesHashMap = new HashMap<String, Properties>();

    @Produces
    @ConfiguredBy("")
    public Properties produceProperties(InjectionPoint ip) {

        String value = ip.getAnnotated().getAnnotation(ConfiguredBy.class).value();

        if (propertiesHashMap.containsKey(value)) {
            return propertiesHashMap.get(value);
        }

        Properties p = new Properties();
        logger.info("Producing properties file: [" + value + "]");
        if (value == null || value.equals("")) {
            return p;
        }

        InputStream is = null;
        try {
            is = this.getClass().getClassLoader().getResourceAsStream(value);
            p.load(is);
            propertiesHashMap.put(value,p);
        } catch (Exception e) {
            logger.warning("Problem reading the file!");
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    logger.warning("Problem closing the file!");
                }
            }
        }
        return p;
    }
}
