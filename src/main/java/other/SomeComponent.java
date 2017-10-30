package other;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class SomeComponent implements InitializingBean, DisposableBean {

    public void init() {
        System.out.print("e -SC -init ");
    }

    public void close() {
        System.out.print("f");
    }

@Override
public void afterPropertiesSet() throws Exception {
        System.out.print("g -SC -after ");
        }

@Override
public void destroy() throws Exception {
        System.out.print("h");
        }

public void print() {
        System.out.print("i -SC - print ");
        }
        }