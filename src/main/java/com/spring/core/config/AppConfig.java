package com.spring.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.text.DateFormat;
import java.util.Date;

@Configuration
public class AppConfig {

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

}
