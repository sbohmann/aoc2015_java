package day1;

import common.InputFile;

class B {
    private String input;
    private int result;

    public static void main(String[] args) {
        new B().run();
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
        result = new BSolution(input).result;
    }

    private void printResult() {
        System.out.println(result);
    }
}

