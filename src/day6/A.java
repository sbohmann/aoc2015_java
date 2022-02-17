package day6;

import common.InputFile;

public class A {
    private final Lights lights = new Lights(1000, 1000);

    public static void main(String[] args) {
        new A().solve();
    }

    private void solve() {
        InputFile.lines(getClass()).foreach(this::processLine);
        System.out.println(lights.numberTurnedOn());
    }

    private void processLine(String text) {
        Line line = new Line(text);
        executeCommand(line.command, line.area);
    }

    private void executeCommand(Command command, Rectangle area) {
        switch (command) {
            case TurnOn -> lights.turnOn(area);
            case TurnOff -> lights.turnOff(area);
            case Toggle -> lights.toggle(area);
        }
    }
}
