package day3;

import common.InputFile;

public class A {
    private final Houses houses = new Houses();
    private Position currentPosition = new Position(0, 0);

    public static void main(String[] args) {
        new A().solve();
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
        currentPosition = currentPosition.movedBy(direction);
        handleCurrentPosition();

    }

    private void handleCurrentPosition() {
        houses.visit(currentPosition);
    }
}
