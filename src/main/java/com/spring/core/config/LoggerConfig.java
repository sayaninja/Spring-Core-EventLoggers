package com.spring.core.config;

import com.spring.core.logger.CacheFileEventLogger;
import com.spring.core.logger.CombinedEventLogger;
import com.spring.core.logger.ConsoleEventLogger;
import com.spring.core.logger.EventLogger;
import com.spring.core.logger.FileEventLogger;
import com.spring.core.model.EventType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class LoggerConfig {

    private static final String FILENAME = "events_log.txt";
    private static final int CACHE_SIZE = 5;

    @Bean(name = "consoleEventLogger")
    public EventLogger consoleEventLogger() {
        return new ConsoleEventLogger();
    }

    @Bean(name = "fileEventLogger")
    public EventLogger fileEventLogger() {
        return new FileEventLogger(FILENAME);
    }

    @Bean(name = "combinedEventLogger")
    public EventLogger combinedEventLogger() {
        return new CombinedEventLogger();
    }

    @Bean(name = "cacheEventLogger")
    public EventLogger cacheEventLogger() {
        return new CacheFileEventLogger(FILENAME, CACHE_SIZE);
    }

    @Bean
    public Collection<EventLogger> combinedLoggers() {
        Collection<EventLogger> loggers = new ArrayList<>();
        loggers.add(consoleEventLogger());
        loggers.add(fileEventLogger());
        return loggers;
    }

    @Bean
    public Map<EventType, EventLogger> loggerMap() {
        Map<EventType, EventLogger> map = new HashMap<>();
        map.put(EventType.INFO, fileEventLogger());
        map.put(EventType.ERROR, cacheEventLogger());
        return map;
    }

    @Bean(name = "defaultLogger")
    public EventLogger defaultLogger() {
        return consoleEventLogger();
    }

}

