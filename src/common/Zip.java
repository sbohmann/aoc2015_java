package common;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Zip {
    public static <T> Iterable<ZipEntry<T>> withIndex(Iterable<T> original) {
        var originalIterator = original.iterator();
        return zippedIterable(originalIterator);
    }

    public static Iterable<ZipEntry<Character>> withIndex(String text) {
        var originalIterator = new Iterator<Character>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                return index < text.length();
            }

            @Override
            public Character next() {
                return text.charAt(index++);
            }
        };
        return zippedIterable(originalIterator);
    }

    public static Stream<ZipEntry<Integer>> withIndex(IntStream stream) {
        var zippedIterator = zippedIterator(stream.iterator());
        return StreamSupport.stream(
                Spliterators.spliteratorUnknownSize(
                        zippedIterator,
                        Spliterator.ORDERED),
                false);
    }

    private static <T> Iterable<ZipEntry<T>> zippedIterable(Iterator<T> originalIterator) {
        return () -> zippedIterator(originalIterator);
    }

    private static <T> Iterator<ZipEntry<T>> zippedIterator(Iterator<T> originalIterator) {
        return new Iterator<>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return originalIterator.hasNext();
            }

            @Override
            public ZipEntry<T> next() {
                return new ZipEntry<>(originalIterator.next(), index++);
            }
        };
    }
}
