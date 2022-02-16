package mini;

public interface MutableIndexAccess<T> extends IndexAccess<T> {
    MutableIndexAccess<T> set(int index, T value);

    MutableIndexAccess<T> add(T value);

    MutableIndexAccess<T> addAll(Sequence<? extends T> value);
}
