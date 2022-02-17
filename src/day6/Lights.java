package day6;

class Lights {
    private final int width;
    private final boolean[] data;

    Lights(int width, int height) {
        this.width = width;
        data = new boolean[width * height];
    }

    private boolean get(int x, int y) {
        return data[y * width + x];
    }

    private void set(int x, int y, boolean value) {
        data[y * width + x] = value;
    }

    void turnOn(Rectangle area) {
        area.forEachPosition((x, y) -> set(x, y, true));
    }

    void turnOff(Rectangle area) {
        area.forEachPosition((x, y) -> set(x, y, false));
    }

    void toggle(Rectangle area) {
        area.forEachPosition((x, y) -> set(x, y, !get(x, y)));
    }

    public int numberTurnedOn() {
        int result = 0;
        for (var light : data) {
            if (light) {
                ++result;
            }
        }
        return result;
    }
}
