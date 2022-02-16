package mini.lazy;

import mini.Sequence;

import java.util.Iterator;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public final class FlatMappedSequence<T, U> implements Sequence<U> {
    private final Sequence<T> source;
    private final Function<? super T, Sequence<? extends U>> transformation;

    public FlatMappedSequence(Sequence<T> source, Function<? super T, Sequence<? extends U>> transformation) {
        this.source = source;
        this.transformation = transformation;
    }

    @Override
    public void foreach(Consumer<? super U> action) {
        source.foreach(element -> transformation.apply(element).foreach(action));
    }

    @Override
    public <V> V reduce(V initialValue, BiFunction<V, ? super U, ? extends V> processing) {
        var result = initialValue;
        for (var element : source) {
            for (var innerElement : transformation.apply(element)) {
                result = processing.apply(result, innerElement);
            }
        }
        return result;
    }

    @Override
    public Iterator<U> iterator() {
        return new Iterator<>() {
            final Iterator<T> sourceIterator = source.iterator();
            Iterator<? extends U> innerIterator;

            @Override
            public boolean hasNext() {
                if (innerIterator != null && innerIterator.hasNext()) {
                    return true;
                }
                if (sourceIterator.hasNext()) {
                    innerIterator = transformation.apply(sourceIterator.next()).iterator();
                    return innerIterator.hasNext();
                }
                return false;
            }

            @Override
            public U next() {
                return innerIterator.next();
            }
        };
    }
}
