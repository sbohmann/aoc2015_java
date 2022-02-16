package mini;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public interface Sequence<T> {
    void foreach(Consumer<? super T> action);

    <U> Sequence<U> map(Function<? super T, U> transformation);

    <U> Sequence<U> flatmap(Function<? super T, Sequence<U>> transformation);

    <U> U reduce(U initialValue, BiFunction<U, ? super T, ? extends U> processing);
}
