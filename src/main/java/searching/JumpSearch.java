package searching;

/**
 * https://www.geeksforgeeks.org/jump-search/
 */
public class JumpSearch {

    private JumpSearch() {}

    public static <T> int jumpSearch(Comparable<T>[] arr, Comparable<T> x) {
        int n = arr.length;

        // Finding block size to be jumped
        int step = (int)Math.floor(Math.sqrt(n));

        // Finding the block where element is
        // present (if it is present)
        int prev = 0;
        while (less(arr[Math.min(step, n)-1], x)) {
            prev = step;
            step += (int)Math.floor(Math.sqrt(n));
            if (prev >= n)
                return -1;
        }

        // Doing a linear search for x in block
        // beginning with prev.
        while (less(arr[prev], x)) {
            prev++;

            // If we reached next block or end of
            // array, element is not present.
            if (prev == Math.min(step, n))
                return -1;
        }

        // If element is found
        if (equalTo(arr[prev], x))
            return prev;

        return -1;
    }

    private static <T> boolean less(Comparable<T> v, Comparable<T> w) {
        return v.compareTo((T) w) < 0;
    }

    private static <T> boolean equalTo(Comparable<T> v, Comparable<T> w) {
        return v.compareTo((T) w) == 0;
    }

}
