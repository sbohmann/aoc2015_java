package day4;

import common.InputFile;
import common.MD5Hash;

public class AB {
    private static final String EXPECTED_HASH_PREFIX_A = "00000";
    private static final String EXPECTED_HASH_PREFIX_B = "000000";

    private String expectedHashPrefix;
    private String input;

    public static void main(String[] args) {
        new AB().solve(EXPECTED_HASH_PREFIX_A);
        new AB().solve(EXPECTED_HASH_PREFIX_B);
    }

    private void solve(String expectedHashPrefix) {
        this.expectedHashPrefix = expectedHashPrefix;
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
        String hash = MD5Hash.forAsciiString(text);
        return hash.startsWith(expectedHashPrefix);
    }
}
