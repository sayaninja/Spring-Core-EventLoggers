package com.spring.core.logger;

import com.spring.core.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Collection;

public class CombinedEventLogger implements EventLogger {

    @Autowired
    private Collection<EventLogger> loggers;

    @Override
    public void logEvent(Event event) {
        for (EventLogger logger: loggers) {
            logger.logEvent(event);
        }
    }
}
