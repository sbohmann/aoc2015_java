package day6;

import java.util.function.BiConsumer;

class Rectangle {
    final int firstX;
    final int firstY;
    final int lastX;
    final int lastY;

    Rectangle(int firstX, int firstY, int lastX, int lastY) {
        this.firstX = firstX;
        this.firstY = firstY;
        this.lastX = lastX;
        this.lastY = lastY;
    }

    void forEachPosition(BiConsumer<Integer, Integer> handlePosition) {
        for (var y = firstY; y <= lastY; ++y) {
            for (var x = firstX; x <= lastX; ++x) {
                handlePosition.accept(x, y);
            }
        }
    }
}
