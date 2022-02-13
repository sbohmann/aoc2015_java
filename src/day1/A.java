package day1;

import common.InputFile;

class A {
    private String input;
    private int result;

    public static void main(String[] args) {
        new A().run();
    }

    private void run() {
        readInput();
        solve();
        printResult();
    }

    private void readInput() {
        input = InputFile.read(getClass());
    }

    private void solve() {
        result = input.codePoints()
                .reduce(0, this::adaptedValue);
    }

    private int adaptedValue(int currentValue, int c) {
        switch (c) {
            case '(':
                return currentValue + 1;
            case ')':
                return currentValue - 1;
            default:
                throw new IllegalArgumentException("Unsupported input character [" + c + "]");
        }
    }

    private void printResult() {
        System.out.println(result);
    }
}
