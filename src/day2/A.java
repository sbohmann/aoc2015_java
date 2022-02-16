package day2;

import common.InputFile;

public class A {
    private int totalArea = 0;

    public static void main(String[] args) {
        new A().solve();
    }

    private void solve() {
        InputFile.lines(getClass()).foreach(this::processLine);
        System.out.println(totalArea);
    }

    private void processLine(String line) {
        totalArea += new Parcel(line).area();
    }
}
