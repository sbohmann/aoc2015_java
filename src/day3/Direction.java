package day3;

enum Direction {
    UP(0, -1),
    DOWN(0, 1),
    LEFT(-1, 0),
    RIGHT(1, 0);

    final int deltaX;
    final int deltaY;

    Direction(int deltaX, int deltaY) {
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }

    static Direction read(char c) {
        return switch(c) {
            case '^' -> UP;
            case 'v' -> DOWN;
            case '<' -> LEFT;
            case '>' -> RIGHT;
            default -> throw new IllegalArgumentException("" + c);
        };
    }
}
