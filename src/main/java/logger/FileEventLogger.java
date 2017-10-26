package logger;

import lombok.NoArgsConstructor;
import model.Event;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

@NoArgsConstructor
public class FileEventLogger implements EventLogger {

    private File file;
    private String filename;

    public FileEventLogger(String fileName) {
        this.filename = fileName;
        init();
    }

    public void setFilename(String filename) {
        this.filename = filename;
        init();
    }

    private void init() {
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
