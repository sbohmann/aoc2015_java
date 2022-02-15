package day3;

import java.util.Objects;

class Position {
    final int x;
    final int y;

    Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    Position movedBy(Direction direction) {
        return new Position(x + direction.deltaX, y + direction.deltaY);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
