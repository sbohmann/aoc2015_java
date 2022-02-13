package common;

import java.util.Objects;

public class ZipEntry<T> {
    public final T value;
    public final int index;

    public ZipEntry(T value, int index) {
        this.value = Objects.requireNonNull(value);
        this.index = index;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ZipEntry<?> zipEntry = (ZipEntry<?>) o;
        return index == zipEntry.index && value.equals(zipEntry.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, index);
    }
}
