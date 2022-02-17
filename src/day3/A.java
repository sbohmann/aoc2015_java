package day3;

import common.InputFile;

import java.util.HashSet;
import java.util.Set;

public class A {
    private final Set<Position> housesVisited = new HashSet<>();
    private Position currentPosition = new Position(0, 0);

    public static void main(String[] args) {
        new A().solve();
    }

    private void solve() {
        handleCurrentPosition();
        processInputFile();
        System.out.println(housesVisited.size());
    }

    private void processInputFile() {
        InputFile.characters(getClass()).foreach(this::handleInputCharacter);
    }

    private void handleInputCharacter(char c) {
        handleDirection(Direction.read(c));
    }

    private void handleDirection(Direction direction) {
        currentPosition = currentPosition.movedBy(direction);
        handleCurrentPosition();
    }

    private void handleCurrentPosition() {
        housesVisited.add(currentPosition);
    }
}
