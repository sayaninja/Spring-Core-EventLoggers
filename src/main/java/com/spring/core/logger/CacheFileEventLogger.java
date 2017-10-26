package com.spring.core.logger;

import lombok.NoArgsConstructor;
import com.spring.core.model.Event;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class CacheFileEventLogger extends FileEventLogger {

    private int cacheSize;
    private List<Event> cache;

    public CacheFileEventLogger(String filename, int cacheSize){
        super(filename);
        this.cacheSize = cacheSize;
    }

    public void setCacheSize(int cacheSize) {
        this.cacheSize = cacheSize;
    }
    public void initCache() {
        this.cache = new ArrayList<Event>(cacheSize);
    }

    public void logEvent(Event event) {
        cache.add(event);
        if (cache.size() == cacheSize) {
            writeEventsToFile();
            cache.clear();
        }
    }

    public void destroy() {
        if (cache.isEmpty() == false) {
            writeEventsToFile();
        }
    }

    private void writeEventsToFile() {
        cache.forEach(super::logEvent);
    }
}
