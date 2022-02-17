package day5;

class BTextInterpretation {
    final boolean nice;

    public BTextInterpretation(String text) {
        nice = separatedPairsPresent(text)
                && new DuplicatePairs(text).present;
    }

    static boolean nice(String text) {
        return new BTextInterpretation(text).nice;
    }

    private boolean separatedPairsPresent(String text) {
        for (var index = 0; index < text.length() - 2; ++index) {
            if (lookingAtSeparatedPair(text, index)) {
                return true;
            }
        }
        return false;
    }

    private boolean lookingAtSeparatedPair(String text, int index) {
        return text.charAt(index) != text.charAt(index + 1)
                && text.charAt(index) == text.charAt(index + 2);
    }
}
