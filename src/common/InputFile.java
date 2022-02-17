package common;

import mini.Files;
import mini.Sequence;

import java.io.IOException;
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

    public static Sequence<Character> characters(Class<?> context) {
        return Files.charactersForClassPath(context, inputFileName);
    }
}
