import logger.EventLogger;
import logger.FileEventLogger;
import lombok.AllArgsConstructor;
import model.Client;
import model.Event;
import model.EventType;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.Map;

@AllArgsConstructor
public class App {
    private Client client;
    private FileEventLogger defaultLogger;
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
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        App app = (App) context.getBean("app");
        Event event = (Event) context.getBean("event");
        app.logEvent(EventType.INFO, event, "Some info message for 1");

        event = (Event) context.getBean("event");
        app.logEvent(EventType.ERROR, event, "Some error message for 2");

        context.close();
    }
}
