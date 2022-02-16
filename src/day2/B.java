package day2;

import common.InputFile;

public class B {
    private int totalRibbonLength = 0;

    public static void main(String[] args) {
        new B().solve();
    }

    private void solve() {
        InputFile.lines(getClass()).foreach(this::processLine);
        System.out.println(totalRibbonLength);
    }

    private void processLine(String line) {
        totalRibbonLength += new Parcel(line).ribbonLength();
    }
}
