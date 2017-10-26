import logger.FileEventLogger;
import lombok.AllArgsConstructor;
import model.Client;
import model.Event;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@AllArgsConstructor
public class App {
    private Client client;
    private FileEventLogger eventLogger;

    public void logEvent(Event event, String msg) {
        String newMsg = msg.replaceAll(client.getId(), client.getFullName());
        event.setMsg(newMsg);
        eventLogger.logEvent(event);
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        App app = (App) context.getBean("app");
        Event event = (Event) context.getBean("event");
        app.logEvent(event, "Some message for 1");
        context.close();
    }
}
