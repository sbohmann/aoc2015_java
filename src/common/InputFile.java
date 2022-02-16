package common;

import mini.Files;
import mini.Sequence;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class InputFile {
    private static final String inputFileName = "input.txt";

    // TODO read entire file via mini
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

    public static Sequence<String> lines(Class<?> context) {
        return Files.linesForClassPath(context, inputFileName);
    }

    public interface CharConsumer {
        void accept(char value);
    }

    // TODO replace with Sequence
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
