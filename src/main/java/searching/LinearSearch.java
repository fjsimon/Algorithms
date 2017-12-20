package searching;

/**
 * Info: http://www.geeksforgeeks.org/linear-search/
 */
public class LinearSearch {

    private LinearSearch() {}

    // This function returns index of element x in arr[]
    public static <T> int search(Comparable<T> arr[], int n, Comparable<T> x) {

        for (int i = 0; i < n; i++) {

            // Return the index of the element if the element is found
            if (equalTo(arr[i], x))
                return i;
        }

        // return -1 if the element is not found
        return -1;
    }

    private static <T> boolean equalTo(Comparable<T> v, Comparable<T> w) {

        return v.compareTo((T) w) == 0;
    }
}