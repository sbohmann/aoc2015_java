package mini;

import mini.lazy.FlatMappedSequence;
import mini.lazy.MappedSequence;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

class LinesOfFile implements Sequence<String> {
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

    @Override
    public void foreach(Consumer<? super String> action) {
        var reader = createBufferedReader();
        while (true) {
            var line = readLine(reader);
            if (line == null) {
                break;
            }
            action.accept(line);
        }
    }

    @Override
    public <U> Sequence<U> map(Function<? super String, ? extends U> transformation) {
        return new MappedSequence<>(this, transformation);
    }

    @Override
    public <U> Sequence<U> flatmap(Function<? super String, Sequence<? extends U>> transformation) {
        return new FlatMappedSequence<>(this, transformation);
    }

    @Override
    public <U> U reduce(U initialValue, BiFunction<U, ? super String, ? extends U> processing) {
        var result = initialValue;
        for (var element : this) {
            result = processing.apply(result, element);
        }
        return result;
    }

    private BufferedReader createBufferedReader() {
        try {
            return new BufferedReader(new FileReader(file, StandardCharsets.UTF_8));
        } catch (IOException error) {
            throw new IllegalStateException(error);
        }
    }

    private String readLine(BufferedReader reader) {
        try {
            return reader.readLine();
        } catch (IOException error) {
            throw new IllegalStateException(error);
        }
    }

    @Override
    public Iterator<String> iterator() {
        var reader = createBufferedReader();

        return new Iterator<>() {
            private String next;

            @Override
            public boolean hasNext() {
                next = readLine(reader);
                return next != null;
            }

            @Override
            public String next() {
                return next;
            }
        };
    }
}
