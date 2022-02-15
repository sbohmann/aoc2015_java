package day5;

import common.InputFile;

import java.util.stream.Stream;

public class A {
    public static void main(String[] args) {
        new A().solve();
    }

    private void solve() {
        readInput().forEach(line -> System.out.println('[' + line + ']'));
    }

    private Stream<String> readInput() {
        return InputFile.linesAsStream(getClass());
    }
}
