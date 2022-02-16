package mini;

public class Files {
    public static Sequence<String> linesOfFile(String path) {
        return new LinesOfFile(path);
    }

    public static Sequence<String> linesForClassPath(Class<?> context, String path) {
        return new LinesOfResource(context, path);
    }
}
