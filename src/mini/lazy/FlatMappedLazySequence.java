package mini.lazy;

import mini.Sequence;

import java.util.Iterator;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public final class FlatMappedLazySequence<T, U> implements Sequence<U> {
    private final Sequence<T> source;
    private final Function<? super T, Sequence<? extends U>> transformation;

    public FlatMappedLazySequence(Sequence<T> source, Function<? super T, Sequence<? extends U>> transformation) {
        this.source = source;
        this.transformation = transformation;
    }

    @Override
    public void foreach(Consumer<? super U> action) {
        source.foreach(element -> transformation.apply(element).foreach(action));
    }

    @Override
    public <V> Sequence<V> map(Function<? super U, ? extends V> nextTransformation) {
        return new MappedLazySequence<>(this, nextTransformation);
    }

    @Override
    public <V> Sequence<V> flatmap(Function<? super U, Sequence<? extends V>> nextTransformation) {
        return new FlatMappedLazySequence<>(this, nextTransformation);
    }

    @Override
    public <V> V reduce(V initialValue, BiFunction<V, ? super U, ? extends V> processing) {
        return null;
    }

    @Override
    public Iterator<U> iterator() {
        return null;
    }
}
