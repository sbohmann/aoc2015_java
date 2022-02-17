package day6;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Line {
    private static final Pattern pattern = Pattern.compile("(.+) (\\d+),(\\d+) through (\\d+),(\\d+)");

    private final Matcher matcher;

    final Command command;
    final Rectangle area;

    Line(String source) {
        matcher = pattern.matcher(source);
        if (!matcher.matches()) {
            throw new IllegalArgumentException(source);
        }
        command = readCommand();
        area = readArea();
    }

    private Command readCommand() {
        return Command.forDescription(matcher.group(1));
    }

    private Rectangle readArea() {
        return new Rectangle(parseInt(2), parseInt(3), parseInt(4), parseInt(5));
    }

    private int parseInt(int index) {
        return Integer.parseInt(matcher.group(index));
    }
}
