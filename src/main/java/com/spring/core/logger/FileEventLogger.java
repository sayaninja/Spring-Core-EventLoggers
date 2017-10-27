package com.spring.core.logger;

import com.spring.core.model.Event;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

public class FileEventLogger implements EventLogger {

    private String filename;
    private File file;

    public FileEventLogger(String fileName) {
        this.filename = fileName;
        try {
            file = new File(filename);
            if (file.exists() && file.canWrite() == false) {
                throw new IllegalArgumentException("Can't write to file " + filename);
            } else if (file.exists() == false) {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void logEvent(Event event) {
        try {
            FileUtils.writeStringToFile(file, event.toString(), Charset.forName("utf-8"), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
