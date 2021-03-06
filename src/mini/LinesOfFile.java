package mini;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

class LinesOfFile extends LinesForReader {
    private final File file;

    public LinesOfFile(String path) {
        this.file = new File(path);
        checkFile();
    }

    private void checkFile() {
        if (!file.isFile()) {
            throw new IllegalArgumentException("Not a file: [" + file.getAbsolutePath() + "]" +
                    " for path [" + file.getPath() + "]");
        }
    }

    protected BufferedReader createBufferedReader() {
        try {
            return new BufferedReader(new FileReader(file, StandardCharsets.UTF_8));
        } catch (IOException error) {
            throw new IllegalStateException(error);
        }
    }
}
