package mini.lazy;

import mini.Sequence;

import java.util.Iterator;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public final class MappedSequence<T, U> implements Sequence<U> {
    private final Sequence<T> source;
    private final Function<? super T, ? extends U> transformation;

    public MappedSequence(Sequence<T> source, Function<? super T, ? extends U> transformation) {
        this.source = source;
        this.transformation = transformation;
    }

    @Override
    public void foreach(Consumer<? super U> action) {
        source.foreach(element -> action.accept(transformation.apply(element)));
    }

    @Override
    public <V> V reduce(V initialValue, BiFunction<V, ? super U, ? extends V> processing) {
        var result = initialValue;
        for (var element : source) {
            result = processing.apply(result, transformation.apply(element));
        }
        return result;
    }

    @Override
    public Iterator<U> iterator() {
        return new Iterator<>() {
            final Iterator<T> sourceIterator = source.iterator();

            @Override
            public boolean hasNext() {
                return sourceIterator.hasNext();
            }

            @Override
            public U next() {
                return transformation.apply(sourceIterator.next());
            }
        };
    }
}
