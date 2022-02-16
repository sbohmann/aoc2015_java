package mini;

public class Files {
    public static Sequence<String> linesOfFile(String path) {
        return new LinesOfFile(path);
    }
}
