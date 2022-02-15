package day3;

import common.InputFile;

public class B {
    private final Houses houses = new Houses();
    private final AlternatingPosition currentPosition = new AlternatingPosition();

    public static void main(String[] args) {
        new B().solve();
    }

    private void solve() {
        handleCurrentPosition();
        processInputFile();
        System.out.println(houses.numberOfHousesVisited());
    }

    private void processInputFile() {
        InputFile.forEachCharacter(getClass(), this::handleInputCharacter);
    }

    private void handleInputCharacter(char c) {
        handleDirection(Direction.read(c));
    }

    private void handleDirection(Direction direction) {
        currentPosition.set(currentPosition.value().movedBy(direction));
        handleCurrentPosition();
        currentPosition.swap();
    }

    private void handleCurrentPosition() {
        houses.visit(currentPosition.value());
    }
}
