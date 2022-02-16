package day5;

import common.InputFile;
import mini.Sequence;

public class A {
    public static void main(String[] args) {
        new A().solve();
    }

    private void solve() {
        var result = readInput()
                .filter(TextInterpretation::nice)
                .count();
        System.out.println(result);
    }

    private Sequence<String> readInput() {
        return InputFile.lines(getClass());
    }
}
