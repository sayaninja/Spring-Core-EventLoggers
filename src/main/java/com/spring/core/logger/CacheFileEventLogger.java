package com.spring.core.logger;

import com.spring.core.model.Event;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;

public class CacheFileEventLogger extends FileEventLogger {

    private int cacheSize;
    private List<Event> cache;

    public CacheFileEventLogger(String filename, int cacheSize){
        super(filename);
        this.cacheSize = cacheSize;
        this.cache = new ArrayList<>(cacheSize);
    }

    @Override
    public void logEvent(Event event) {
        cache.add(event);
        if (cache.size() == cacheSize) {
            writeEventsToFile();
            cache.clear();
        }
    }

    @PreDestroy
    public void destroy() {
        if (cache.isEmpty() == false) {
            writeEventsToFile();
        }
    }

    private void writeEventsToFile() {
        cache.forEach(super::logEvent);
    }
}
