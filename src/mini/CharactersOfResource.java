package mini;

import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

class CharactersOfResource extends CharactersForReader {
    private final Class<?> context;
    private final String path;

    public CharactersOfResource(Class<?> context, String path) {
        this.context = context;
        this.path = path;
    }

    protected Reader createReader() {
        return new InputStreamReader(
                Objects.requireNonNull(context.getResourceAsStream(path)),
                StandardCharsets.UTF_8);
    }
}
