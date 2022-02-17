package mini;

import mini.lazy.FlatMappedSequence;
import mini.lazy.MappedSequence;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

abstract class LinesForReader implements Sequence<String> {
    protected abstract BufferedReader createBufferedReader();

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
            private boolean finished;

            @Override
            public boolean hasNext() {
                if (finished) {
                    return false;
                }
                if (next != null) {
                    return true;
                }
                next = readLine(reader);
                if (next == null) {
                    finished = true;
                    return false;
                }
                return true;
            }

            @Override
            public String next() {
                String result = next;
                next = null;
                return result;
            }
        };
    }
}
