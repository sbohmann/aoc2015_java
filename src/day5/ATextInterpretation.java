package day5;

import java.util.Set;
import java.util.regex.Pattern;

class ATextInterpretation {
    private static final Set<Character> vowels = Set.of('a', 'e', 'i', 'o', 'u');
    private static final Pattern offensiveWordPattern = Pattern.compile(
            "ab|cd|pq|xy"
    );

    private final String text;

    final boolean nice;

    public ATextInterpretation(String text) {
        this.text = text;
        nice = vowelCount() >= 3
                && containsRepetitions()
                && !containsOffensiveText();
    }

    static boolean nice(String text) {
        return new ATextInterpretation(text).nice;
    }

    private long vowelCount() {
        return text.chars()
                .filter(c -> vowels.contains((char) c))
                .count();
    }

    private boolean containsRepetitions() {
        char last = text.charAt(0);
        for (var index = 1; index < text.length(); ++index) {
            char next = text.charAt(index);
            if (next == last) {
                return true;
            }
            last = next;
        }
        return false;
    }

    private boolean containsOffensiveText() {
        return offensiveWordPattern.matcher(text)
                .find();
    }
}
