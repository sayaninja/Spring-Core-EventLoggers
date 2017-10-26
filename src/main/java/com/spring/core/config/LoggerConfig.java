package com.spring.core.config;

import com.spring.core.logger.EventLogger;
import org.springframework.context.annotation.Configuration;
import javax.annotation.Resource;

@Configuration
public class LoggerConfig {
    @Resource(name = "consoleEventLogger")
    private EventLogger consoleEventLogger;

    @Resource(name = "fileEventLogger")
    private EventLogger fileEventLogger;

    @Resource(name = "combinedEventLogger")
    private EventLogger combinedEventLogger;

    @Resource(name = "cacheFileEventLogger")
    private EventLogger cacheEventLogger;
}
