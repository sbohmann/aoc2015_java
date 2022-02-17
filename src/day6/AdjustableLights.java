package day6;

class AdjustableLights {
    private final int width;
    private final int[] data;

    AdjustableLights(int width, int height) {
        this.width = width;
        data = new int[width * height];
    }

    private int get(int x, int y) {
        return data[y * width + x];
    }

    private void set(int x, int y, int value) {
        data[y * width + x] = value;
    }

    void turnOn(Rectangle area) {
        area.forEachPosition((x, y) -> set(x, y, get(x, y) + 1));
    }

    void turnOff(Rectangle area) {
        area.forEachPosition((x, y) -> {
            int value = get(x, y);
            if (value > 0) {
                set(x, y, value - 1);
            }
        });
    }

    void toggle(Rectangle area) {
        area.forEachPosition((x, y) -> set(x, y, get(x, y) + 2));
    }

    public int sumOfLights() {
        int result = 0;
        for (var light : data) {
            result += light;
        }
        return result;
    }
}
