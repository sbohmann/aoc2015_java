package mini;

import java.util.Iterator;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public interface Sequence<T> extends Iterable<T> {
    void foreach(Consumer<? super T> action);

    <U> Sequence<U> map(Function<? super T, ? extends U> transformation);

    <U> Sequence<U> flatmap(Function<? super T, Sequence<? extends U>> transformation);

    default <U> U reduce(U initialValue, BiFunction<U, ? super T, ? extends U> processing) {
        throw new UnsupportedOperationException("Attempt to call reduce() on a non-iterable Sequence");
    }

    @Override
    default Iterator<T> iterator() {
        throw new UnsupportedOperationException("Attempt to call iterator() on a non-iterable Sequence");
    }
}
