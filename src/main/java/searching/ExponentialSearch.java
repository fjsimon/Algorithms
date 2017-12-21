package searching;

import java.util.Arrays;

/**
 * https://www.geeksforgeeks.org/exponential-search/
 */
public class ExponentialSearch {

    private ExponentialSearch() {}

    // Returns position of first occurrence of x in array
    public static <T> int exponentialSearch(Comparable<T> arr[], Comparable<T> x) {
        int n = arr.length;
        // If x is present at first location itself
        if (equalTo(arr[0], x)) {
            return 0;
        }

        // Find range for binary search by repeated doubling
        int i = 1;
        while (i < n && lessOrEqualTo(arr[i], x)) {
            i = i * 2;
        }

        //  Call binary search for the found range.
        return Arrays.binarySearch(arr, i/2, Math.min(i, n), x);
    }

    private static <T> boolean lessOrEqualTo(Comparable<T> v, Comparable<T> w) {

        return v.compareTo((T) w) <= 0;
    }

    private static <T> boolean equalTo(Comparable<T> v, Comparable<T> w) {

        return v.compareTo((T) w) == 0;
    }

}
