package mini.lazy;

import mini.Sequence;

import java.util.Iterator;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public final class FilteredSequence<T> implements Sequence<T> {
    private final Sequence<T> source;
    private final Predicate<? super T> predicate;

    public FilteredSequence(Sequence<T> source, Predicate<? super T> predicate) {
        this.source = source;
        this.predicate = predicate;
    }

    @Override
    public void foreach(Consumer<? super T> action) {
        source.foreach(element -> {
            if (predicate.test(element)) {
                action.accept(element);
            }
        });
    }

    @Override
    public <U> U reduce(U initialValue, BiFunction<U, ? super T, ? extends U> processing) {
        var result = initialValue;
        for (var element : source) {
            if (predicate.test(element)) {
                result = processing.apply(result, element);
            }
        }
        return result;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            final Iterator<T> sourceIterator = source.iterator();
            Optional<T> next = Optional.empty();
            boolean finished;

            @Override
            public boolean hasNext() {
                if (finished) {
                    return false;
                }
                while (next.isEmpty() && sourceIterator.hasNext()) {
                    var candidate = sourceIterator.next();
                    if (predicate.test(candidate)) {
                        next = Optional.of(candidate);
                        return true;
                    }
                }
                return next.isPresent();
            }

            @Override
            public T next() {
                var result = next.orElseThrow();
                next = Optional.empty();
                return result;
            }
        };
    }
}
