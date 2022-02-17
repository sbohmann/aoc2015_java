package day5;

public class DuplicatePairs {
    private final String text;
    boolean present;

    public DuplicatePairs(String text) {
        this.text = text;
        searchForDuplicatedPair();
    }

    private void searchForDuplicatedPair() {
        for (var index = 0; index < text.length() - 3; ++index) {
            if (searchForDuplicate(text.substring(index, index + 2), index + 2)) {
                present = true;
                break;
            }
        }
    }

    private boolean searchForDuplicate(String original, int start) {
        for (var index = start; index < text.length() - 1; ++index) {
            if (text.substring(index, index + 2).equals(original)) {
                return true;
            }
        }
        return false;
    }
}
