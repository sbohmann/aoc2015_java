package common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.Consumer;

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
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        Objects.requireNonNull(context.getResourceAsStream(inputFileName)),
                        StandardCharsets.UTF_8))) {

            while (true) {
                String line = readLine(reader);
                if (line == null) {
                    break;
                }
                lineHandler.accept(line);
            }
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
}
