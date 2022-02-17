package day7;

import mini.Sequence;

import java.util.HashMap;
import java.util.Map;

class Circuit {
    private final Map<String, Integer> pointValues = new HashMap<>();

    Circuit(Sequence<String> lines) {
        lines.foreach(this::processLine);
    }

    private void processLine(String line) {

    }

    private void addConnection(String line) {

    }
}
