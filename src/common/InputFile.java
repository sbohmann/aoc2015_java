package common;

import day5.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Objects;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class InputFile {
    private static final String inputFileName = "input.txt";

    public static String read(Class<?> context) {
        try {
            var stream = context.getResourceAsStream(inputFileName);
            return new String(
                    Objects.requireNonNull(stream)
                            .readAllBytes(),
                    StandardCharsets.UTF_8);
        } catch (IOException error) {
            throw new RuntimeException(error);
        }
    }

    public static void forEachLine(Class<?> context, Consumer<String> lineHandler) {
        withBufferedReader(context, reader -> {
            while (true) {
                String line = readLine(reader);
                if (line == null) {
                    break;
                }
                lineHandler.accept(line);
            }
        });
    }

    private static void withBufferedReader(Class<?> context, Consumer<BufferedReader> useReader) {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        Objects.requireNonNull(context.getResourceAsStream(inputFileName)),
                        StandardCharsets.UTF_8))) {
            useReader.accept(reader);
        } catch (IOException error) {
            throw new IllegalStateException(error);
        }
    }

    public static Stream<String> linesAsStream(Class<?> context) {
        return StreamSupport.stream(
                Spliterators.spliteratorUnknownSize(
                        lines(context).iterator(),
                        Spliterator.ORDERED),
                false);
    }

    public static Iterable<String> lines(Class<?> context) {
        BufferedReader reader = createBufferedReader(context);
        return () -> new Iterator<>() {
            @Override
            public boolean hasNext() {
                return bufferedReaderHasNextLine(reader);
            }

            @Override
            public String next() {
                return readLine(reader);
            }
        };
    }

    private static BufferedReader createBufferedReader(Class<?> context) {
        return new BufferedReader(
                new InputStreamReader(
                        Objects.requireNonNull(context.getResourceAsStream(inputFileName)),
                        StandardCharsets.UTF_8));
    }

    private static boolean bufferedReaderHasNextLine(BufferedReader reader) {
        try {
            reader.mark(1);
            var result = reader.read() >= 0;
            reader.reset();
            return result;
        } catch (IOException error) {
            throw new IllegalStateException(error);
        }
    }

    private static String readLine(BufferedReader reader) {
        try {
            return reader.readLine();
        } catch (IOException error) {
            throw new IllegalStateException(error);
        }
    }

    public interface CharConsumer {
        void accept(char value);
    }

    public static void forEachCharacter(Class<?> context, CharConsumer charHandler) {
        try (Reader reader = new InputStreamReader(
                Objects.requireNonNull(context.getResourceAsStream(inputFileName)),
                StandardCharsets.UTF_8)) {

            while (true) {
                int input = reader.read();
                if (input < 0) {
                    break;
                }
                charHandler.accept((char) input);
            }
        } catch (IOException error) {
            throw new IllegalStateException(error);
        }
    }
}
