package day2;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parcel {
    private final int DIMENSIONS = 3;
    private static final Pattern pattern =
            Pattern.compile("(\\d+)x(\\d+)x(\\d+)");

    private final int[] dimensions = new int[DIMENSIONS];

    public Parcel(String input) {
        readDimensions(input);
    }

    private void readDimensions(String input) {
        java.util.regex.Matcher matcher = applyPattern(input);
        for (int index = 0; index < dimensions.length; ++index) {
            dimensions[index] = Integer.parseInt(matcher.group(index + 1));
        }
        Arrays.sort(dimensions);
    }

    private Matcher applyPattern(String input) {
        var result = pattern.matcher(input);
        if (!result.matches()) {
            throw new IllegalArgumentException(input);
        }
        return result;
    }

    public int area() {
        var result = 0;
        var first = true;
        for (var lhsIndex = 0; lhsIndex < DIMENSIONS; ++lhsIndex) {
            for (var rhsIndex = lhsIndex + 1; rhsIndex < DIMENSIONS; ++rhsIndex) {
                var factor = (first ? 3 : 2);
                var area = dimensions[lhsIndex] * dimensions[rhsIndex];
                result += factor * area;
                first = false;
            }
        }
        return result;
    }
    
    public int ribbonLength() {
        return wrapAroundLength() + volume();
    }

    private int wrapAroundLength() {
        return 2 * (dimensions[0] + dimensions[1]);
    }

    private int volume() {
        return Arrays.stream(dimensions)
                .reduce(1, (a, b) -> a * b);
    }
}
