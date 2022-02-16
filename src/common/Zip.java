package common;

import mini.Sequence;

import java.util.function.Function;

public class Zip {
    public static <T> Sequence<ZipEntry<T>> withIndex(Sequence<T> original) {
        return original.map(new Function<>() {
            int index = 0;

            @Override
            public ZipEntry<T> apply(T value) {
                return new ZipEntry<>(value, index++);
            }
        });
    }

    public static Sequence<ZipEntry<Character>> withIndex(String text) {
        return withIndex(Sequence.forString(text));
    }
}
