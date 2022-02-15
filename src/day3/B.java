package day3;

import common.InputFile;

import java.util.HashSet;
import java.util.Set;

public class B {
    private final Set<Position> housesVisited = new HashSet<>();
    private final AlternatingPosition currentPosition = new AlternatingPosition();

    public static void main(String[] args) {
        new B().solve();
    }

    private void solve() {
        handleCurrentPosition();
        processInputFile();
        System.out.println(housesVisited.size());
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
        housesVisited.add(currentPosition.value());
    }
}
