package mini;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

class CharactersOfFile extends CharactersForReader {
    private final File file;

    public CharactersOfFile(String path) {
        this.file = new File(path);
        checkFile();
    }

    private void checkFile() {
        if (!file.isFile()) {
            throw new IllegalArgumentException("Not a file: [" + file.getAbsolutePath() + "]" +
                    " for path [" + file.getPath() + "]");
        }
    }

    protected Reader createReader() {
        try {
            return new FileReader(file, StandardCharsets.UTF_8);
        } catch (IOException error) {
            throw new IllegalStateException(error);
        }
    }
}
