package pl.com.zagorski.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author Luke Zagorski
 *         Date 12.01.2014
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Run {

  public static void main(String[] args) {

    SpringApplication.run(Run.class);
  }

  @Bean
  protected ServletContextListener listener() {
    return new ServletContextListener() {
      @Override
      public void contextInitialized(ServletContextEvent sce) {
      }

      @Override
      public void contextDestroyed(ServletContextEvent sce) {
      }
    };
  }
}
