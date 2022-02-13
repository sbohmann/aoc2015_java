package day1;

import common.Zip;

class BSolution {
    private final String input;
    private int floor = 0;
    int result;

    public BSolution(String input) {
        this.input = input;
        calculate();
    }

    private void calculate() {
        for (var inputCharacter : Zip.withIndex(input)) {
            adaptFloor(inputCharacter.value);
            if (floor == -1) {
                result = inputCharacter.index + 1;
                return;
            }
        }
        solutionNotFound();
    }

    private void adaptFloor(char inputCharacter) {
        floor = switch (inputCharacter) {
            case '(' -> floor + 1;
            case ')' -> floor - 1;
            default ->
                throw new IllegalArgumentException("Unsupported input character [" + inputCharacter + "]");
        };
    }

    private void solutionNotFound() {
        throw new IllegalStateException("Floor -1 was never reached");
    }
}
