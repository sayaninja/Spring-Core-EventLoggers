package com.spring.core.logger;

import com.spring.core.model.Event;
import org.springframework.stereotype.Component;

@Component
public class ConsoleEventLogger implements EventLogger{

    @Override
    public void logEvent(Event event) {
        System.out.println(event.toString());
    }
}
