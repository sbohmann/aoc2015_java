package common;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class InputFile {
    public static String read(Class<?> context) {
        try {
            var stream = context.getResourceAsStream("input.txt");
            return new String(
                    Objects.requireNonNull(stream)
                            .readAllBytes(),
                    StandardCharsets.UTF_8);
        } catch (IOException error) {
            throw new RuntimeException(error);
        }
    }
}
