package day2;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parcel {
    private final int DIMENSIONS = 3;
    private static final Pattern pattern =
            Pattern.compile("(\\d+)x(\\d+)x(\\d+)");

    private final int[] dimensions = new int[DIMENSIONS];
    public final int area;

    public Parcel(String input) {
        readDimensions(input);
        area = calculateArea();
    }

    private void readDimensions(String input) {
        java.util.regex.Matcher matcher = applyPattern(input);
        for (int index = 0; index < dimensions.length; ++index) {
            dimensions[index] = Integer.parseInt(matcher.group(index + 1));
        }
    }

    private Matcher applyPattern(String input) {
        var result = pattern.matcher(input);
        if (!result.matches()) {
            throw new IllegalArgumentException(input);
        }
        return result;
    }

    private int calculateArea() {
        int[] sortedDimensions = dimensions.clone();
        Arrays.sort(sortedDimensions);
        var result = 0;
        var first = true;
        for (var lhsIndex = 0; lhsIndex < DIMENSIONS; ++lhsIndex) {
            for (var rhsIndex = lhsIndex + 1; rhsIndex < DIMENSIONS; ++rhsIndex) {
                var factor = (first ? 3 : 2);
                var area = sortedDimensions[lhsIndex] * sortedDimensions[rhsIndex];
                result += factor * area;
                first = false;
            }
        }
        return result;
    }
}
