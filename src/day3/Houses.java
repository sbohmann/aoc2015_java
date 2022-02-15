package day3;

import java.util.HashMap;
import java.util.Map;

class Houses {
    private final Map<Position, Integer> data = new HashMap<>();

    void visit(Position position) {
        var currentValue = data.getOrDefault(position, 0);
        data.put(position, currentValue + 1);
    }

    int numberOfHousesVisited() {
        return data.entrySet().size();
    }
}
