package com.spring.core;

import com.spring.core.config.AppConfig;
import com.spring.core.config.LoggerConfig;
import com.spring.core.logger.EventLogger;
import com.spring.core.model.Client;
import com.spring.core.model.Event;
import com.spring.core.model.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class App {

    @Autowired
    private Client client;

    @Autowired
    @Qualifier("defaultLogger")
    private EventLogger defaultLogger;

    @Autowired
    private Map<EventType, EventLogger> loggerMap;

    public void logEvent(EventType type, Event event, String msg) {
        String newMsg = msg.replaceAll(client.getId(), client.getFullName());
        event.setMsg(newMsg);

        EventLogger logger = loggerMap.get(type);
        if (logger == null) {
            logger = defaultLogger;
        }
        logger.logEvent(event);
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AppConfig.class, LoggerConfig.class);
        context.scan("com.spring.core");
        context.refresh();


        App app = (App) context.getBean("app");
        Event event = (Event) context.getBean("event");
        app.logEvent(EventType.INFO, event, "Some info message for 1");

        event = (Event) context.getBean("event");
        app.logEvent(EventType.ERROR, event, "Some error message for 2");

        event = (Event) context.getBean("event");
        app.logEvent(EventType.INFO, event, "Some error message for 2");
        event = (Event) context.getBean("event");
        app.logEvent(EventType.ERROR, event, "Some error message for 2");
        event = (Event) context.getBean("event");
        app.logEvent(EventType.INFO, event, "Some error message for 2");
        event = (Event) context.getBean("event");
        app.logEvent(EventType.ERROR, event, "Some error message for 2");
        context.close();
    }
}
