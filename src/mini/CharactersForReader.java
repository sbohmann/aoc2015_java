package mini;

import mini.lazy.FlatMappedSequence;
import mini.lazy.MappedSequence;

import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

abstract class CharactersForReader implements Sequence<Character> {
    protected abstract Reader createReader();

    @Override
    public void foreach(Consumer<? super Character> action) {
        var reader = createReader();
        while (true) {
            var c = read(reader);
            if (c < 0) {
                break;
            }
            action.accept((char) c);
        }
    }

    @Override
    public <U> Sequence<U> map(Function<? super Character, ? extends U> transformation) {
        return new MappedSequence<>(this, transformation);
    }

    @Override
    public <U> Sequence<U> flatmap(Function<? super Character, Sequence<? extends U>> transformation) {
        return new FlatMappedSequence<>(this, transformation);
    }

    @Override
    public <U> U reduce(U initialValue, BiFunction<U, ? super Character, ? extends U> processing) {
        var result = initialValue;
        for (var element : this) {
            result = processing.apply(result, element);
        }
        return result;
    }

    private int read(Reader reader) {
        try {
            return reader.read();
        } catch (IOException error) {
            throw new IllegalStateException(error);
        }
    }

    @Override
    public Iterator<Character> iterator() {
        var reader = createReader();

        return new Iterator<>() {
            private int next;
            private boolean finished;

            @Override
            public boolean hasNext() {
                if (finished) {
                    return false;
                }
                if (next >= 0) {
                    return true;
                }
                next = read(reader);
                if (next < 0) {
                    finished = true;
                    return false;
                }
                return true;
            }

            @Override
            public Character next() {
                char result = (char) next;
                next = -1;
                return result;
            }
        };
    }
}
