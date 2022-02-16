package mini;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public final class List<T> implements Sequence<T>, MutableIndexAccess<T> {
    private final ArrayList<T> data;

    public List() {
        data = new ArrayList<>();
    }

    private List(Collection<T> source) {
        this.data = new ArrayList<>(source);
    }

    @SafeVarargs
    public static <T> List<T> of(T ... elements) {
        return new List<>(Arrays.asList(elements));
    }

    public static <T> List<T> copyOf(List<T> source) {
        return new List<>(new ArrayList<>(source.data));
    }

    public static <T> List<T> withCapacity(int capacity) {
        return new List<>(new ArrayList<>(capacity));
    }

    @Override
    public void foreach(Consumer<? super T> action) {
        data.forEach(action);
    }

    @Override
    public <U> List<U> map(Function<? super T, ? extends U> transformation) {
        var result = List.<U>withCapacity(data.size());
        for (var element : data) {
            result.add(transformation.apply(element));
        }
        return result;
    }

    @Override
    public <U> List<U> flatmap(Function<? super T, Sequence<? extends U>> transformation) {
        var result = new List<U>();
        for (var element : data) {
            result.addAll(transformation.apply(element));
        }
        return result;
    }

    @Override
    public <U> U reduce(U initialValue, BiFunction<U, ? super T, ? extends U> processing) {
        var result = initialValue;
        for (var element : data) {
            result = processing.apply(result, element);
        }
        return result;
    }

    @Override
    public int size() {
        return data.size();
    }

    @Override
    public T get(int index) {
        return data.get(index);
    }

    @Override
    public MutableIndexAccess<T> set(int index, T value) {
        data.set(index, value);
        return this;
    }

    @Override
    public MutableIndexAccess<T> add(T value) {
        data.add(value);
        return this;
    }

    @Override
    public MutableIndexAccess<T> addAll(Sequence<? extends T> source) {
        if (source instanceof List) {
            addAllFromList(source);
            return this;
        } else {
            addAllFromGenericSequence(source);
            return this;
        }
    }

    @SuppressWarnings("unchecked")
    private void addAllFromList(Sequence<? extends T> source) {
        var sourceAsList = (List<? extends T>) source;
        data.addAll(sourceAsList.data);
    }

    private void addAllFromGenericSequence(Sequence<? extends T> source) {
        if (source instanceof Countable) {
            data.ensureCapacity(data.size() + ((Countable) source).size());
        }
        source.foreach(data::add);
    }

    public Iterator<T> iterator() {
        return data.iterator();
    }
}
