package com.spring.core.config;

import com.spring.core.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import java.text.DateFormat;
import java.util.Date;

@Configuration
@PropertySource("classpath:client.properties")
@ComponentScan(basePackages = "com.spring.core.logger")
public class AppConfig {

    @Autowired
    private Environment environment;

    /**
     * No need to define bean for date and date format
     */
    @Bean
    public Date date() {
        return new Date();
    }

    @Bean
    public DateFormat dateFormat() {
        return DateFormat.getDateTimeInstance();
    }

    @Bean
    public Client client() {
        return Client.builder()
                .id(environment.getRequiredProperty("id"))
                .fullName(environment.getRequiredProperty("name"))
                .greeting(environment.getProperty("greeting"))
                .build();
    }
}
