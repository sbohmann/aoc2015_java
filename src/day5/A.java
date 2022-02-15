package day5;

import common.InputFile;

import java.util.stream.Stream;

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

    private Stream<String> readInput() {
        return InputFile.linesAsStream(getClass())
                .filter(TextInterpretation::nice);
    }
}
