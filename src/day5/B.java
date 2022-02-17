package day5;

import common.InputFile;
import mini.Sequence;

public class B {
    public static void main(String[] args) {
        new B().solve();
    }

    private void solve() {
        var result = readInput()
                .filter(BTextInterpretation::nice)
                .count();
        System.out.println(result);
    }

    private Sequence<String> readInput() {
        return InputFile.lines(getClass());
    }
}
