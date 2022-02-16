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
    public <V> Sequence<V> map(Function<? super U, ? extends V> nextTransformation) {
        return new MappedSequence<>(this, nextTransformation);
    }

    @Override
    public <V> Sequence<V> flatmap(Function<? super U, Sequence<? extends V>> nextTransformation) {
        return new FlatMappedSequence<>(this, nextTransformation);
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
        return null;
    }
}
