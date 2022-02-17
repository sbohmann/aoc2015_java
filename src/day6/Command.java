package day6;

import java.util.HashMap;
import java.util.Map;

public enum Command {
    TurnOn("turn on"),
    TurnOff("turn off"),
    Toggle("toggle");

    private static final Map<String, Command> commandForDescription;

    static {
        commandForDescription = new HashMap<>();
        for (var value : values()) {
            commandForDescription.put(value.description, value);
        }
    }

    private final String description;

    Command(String description) {
        this.description = description;
    }

    public static Command forDescription(String description) {
        var result = commandForDescription.get(description);
        if (result == null) {
            throw new IllegalArgumentException("Unknown command description [" + description + "]");
        }
        return result;
    }
}
