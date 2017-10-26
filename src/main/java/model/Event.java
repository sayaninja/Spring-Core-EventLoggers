package model;

import lombok.Getter;
import lombok.Setter;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class Event {
    private static final AtomicInteger AUTO_ID = new AtomicInteger(0);

    private int id;

    @Setter @Getter
    private String msg;

    @Setter @Getter
    private Date date;

    private DateFormat dateFormat;

    private Event() {
        this.id = AUTO_ID.getAndIncrement();
    }

    public Event(Date date, DateFormat dateFormat) {
        this();
        this.date = date;
        this.dateFormat = dateFormat;
    }

    @Override
    public String toString() {
        return id + " " + msg + " " + dateFormat.format(date);
    }
}
