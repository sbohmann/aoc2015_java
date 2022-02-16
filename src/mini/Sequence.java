package mini;

import mini.lazy.FlatMappedSequence;
import mini.lazy.MappedSequence;

import java.util.Iterator;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public interface Sequence<T> extends Iterable<T> {
    void foreach(Consumer<? super T> action);

    default <U> Sequence<U> map(Function<? super T, ? extends U> transformation) {
        return new MappedSequence<>(this, transformation);
    }

    default <U> Sequence<U> flatmap(Function<? super T, Sequence<? extends U>> transformation) {
        return new FlatMappedSequence<>(this, transformation);
    }

    default <U> U reduce(U initialValue, BiFunction<U, ? super T, ? extends U> processing) {
        throw new UnsupportedOperationException("Attempt to call reduce() on a non-iterable Sequence");
    }

    @Override
    default Iterator<T> iterator() {
        throw new UnsupportedOperationException("Attempt to call iterator() on a non-iterable Sequence");
    }
}
