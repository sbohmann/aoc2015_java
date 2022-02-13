package day1;

import common.InputFile;

class AS {
    public static void main(String[] args) {
        System.out.println(
                InputFile
                        .read(AS.class)
                        .codePoints()
                        .reduce(0, (last, c) -> last + switch (c) {
                            case '(' -> 1;
                            case ')' -> -1;
                            default -> throw new IllegalArgumentException("" + c);
                        }));
    }
}
