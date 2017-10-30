package other;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class SomeService implements InitializingBean, DisposableBean {

    private final SomeComponent component;

    public SomeService(SomeComponent component) {
        this.component = component;
    }

    public void init() {
        System.out.print("a - SS -init ");
    }

    public void close() {
        System.out.print("b -SS -close ");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.print("c");
    }

    @Override
    public void destroy() throws Exception {
        System.out.print("d -SS -destroy ");
    }

    public void call() {
        component.print();
    }
}