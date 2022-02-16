package mini;

import mini.lazy.FilteredSequence;
import mini.lazy.FlatMappedSequence;
import mini.lazy.MappedSequence;

import java.util.Iterator;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public interface Sequence<T> extends Iterable<T> {
    static <T> Sequence<T> forIterable(Iterable<T> source) {
        return new Sequence<>() {
            @Override
            public Iterator<T> iterator() {
                return source.iterator();
            }
        };
    }

    static Sequence<Character> forString(String text) {
        return Sequence.forIterable(() -> text.chars().iterator())
                .map(c -> (Character) (char) c.intValue());
    }

    default void foreach(Consumer<? super T> action){
        for (var element : this) {
            action.accept(element);
        }
    }

    default <U> Sequence<U> map(Function<? super T, ? extends U> transformation) {
        return new MappedSequence<>(this, transformation);
    }

    default <U> Sequence<U> flatmap(Function<? super T, Sequence<? extends U>> transformation) {
        return new FlatMappedSequence<>(this, transformation);
    }

    default Sequence<T> filter(Predicate<? super T> predicate) {
        return new FilteredSequence<>(this, predicate);
    }

    default <U> U reduce(U initialValue, BiFunction<U, ? super T, ? extends U> processing) {
        U result = initialValue;
        for (var element : this) {
            result = processing.apply(result, element);
        }
        return result;
    }

    @Override
    default Iterator<T> iterator() {
        throw new UnsupportedOperationException("Attempt to call iterator() on a non-iterable Sequence");
    }

    default int count() {
        int result = 0;
        for (var element : this) {
            ++result;
        }
        return result;
    }
}
