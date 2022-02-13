package day1;

import common.InputFile;
import common.Zip;

class BS {
    public static void main(String[] args) {
        var floor = 0;
        for (var c : Zip.withIndex(InputFile.read(BS.class))) {
            floor += switch (c.value) {
                case '(' -> 1;
                case ')' -> -1;
                default -> throw new IllegalArgumentException("" + c);
            };
            if (floor == -1) {
                System.out.println(c.index + 1);
                return;
            }
        }
    }
}
