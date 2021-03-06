package mini;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

class LinesOfResource extends LinesForReader {
    private final Class<?> context;
    private final String path;

    public LinesOfResource(Class<?> context, String path) {
        this.context = context;
        this.path = path;
    }

    protected BufferedReader createBufferedReader() {
        return new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(context.getResourceAsStream(path)),
                StandardCharsets.UTF_8));
    }
}
