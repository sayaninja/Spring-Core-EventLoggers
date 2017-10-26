package logger;

import lombok.NoArgsConstructor;
import model.Event;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;

@NoArgsConstructor
public class FileEventLogger implements EventLogger {

    private File file;

    private String filename;

    public FileEventLogger(String fileName) {
        this.filename = fileName;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void init() throws IOException {
        file = new File(filename);
        if (file.exists() && file.canWrite() == false) {
            throw new IllegalArgumentException("Can't write to file " + filename);
        } else if (file.exists() == false) {
            file.createNewFile();
        }
    }

    @Override
    public void logEvent(Event event) {
        try {
            FileUtils.writeStringToFile(file, event.toString() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
