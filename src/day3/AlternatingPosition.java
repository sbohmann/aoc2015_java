package day3;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.rotate;

class AlternatingPosition {
    private final List<Position> positions = new ArrayList<>(List.of(
            new Position(0, 0),
            new Position(0, 0)
    ));

    Position value() {
        return positions.get(0);
    }

    void set(Position position) {
        positions.set(0, position);
    }

    void swap() {
        rotate(positions, -1);
    }
}
