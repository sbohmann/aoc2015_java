package day4;

import common.InputFile;
import common.MD5Hash;

public class A {
    private static final String EXPECTED_HASH_PREFIX = "00000";
    private String input;

    public static void main(String[] args) {
        new A().solve();
    }

    private void solve() {
        input = InputFile.read(getClass()).trim();
        searchForSolution();
    }

    private void searchForSolution() {
        var suffix = 0;
        while (!attemptSolution(suffix)) {
            ++suffix;
        }
    }

    private boolean attemptSolution(int suffix) {
        if (suffixMatches(suffix)) {
            System.out.println(suffix);
            return true;
        }
        return false;
    }

    private boolean suffixMatches(int suffix) {
        String text = input + suffix;
        System.out.println('[' + text + ']');
        String hash = MD5Hash.forAsciiString(text);
        System.out.println(hash);
        return hash
                .startsWith(EXPECTED_HASH_PREFIX);
    }
}
