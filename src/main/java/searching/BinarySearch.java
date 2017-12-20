package searching;

/**
 *
 * Info:
 *
 * http://www.geeksforgeeks.org/binary-search/
 *
 */
public class BinarySearch {

    private BinarySearch() {}

    public static <T> int indexOf(Comparable<T>[] a, Comparable<T> key) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            // Key is in a[lo..hi] or not present.
            int mid = lo + (hi - lo) / 2;
            if      (less(key, a[mid])) hi = mid - 1;
            else if (more(key, a[mid])) lo = mid + 1;
            else return mid;
        }
        return -1;
    }

    public static <T> int binarySearchIteratively(
            Comparable<T>[] sortedArray, Comparable<T> key, int low, int high) {

        int index = Integer.MAX_VALUE;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (less(sortedArray[mid], key)) {
                low = mid + 1;
            } else if (more(sortedArray[mid], key)) {
                high = mid - 1;
            } else if (sortedArray[mid] == key) {
                index = mid;
                break;
            }
        }
        return index;
    }

    public static <T> int binarySearchRecursively(
            Comparable<T> [] sortedArray, Comparable<T> key, int low, int high) {

        int middle = (low + high) / 2;

        if (high < low) {
            return -1;
        }

        if (key == sortedArray[middle]) {
            return middle;
        } else if (less(key, sortedArray[middle])) {
            return binarySearchRecursively(sortedArray, key, low, middle - 1);
        } else {
            return binarySearchRecursively(sortedArray, key, middle + 1, high);
        }
    }

    private static <T> boolean less(Comparable<T> v, Comparable<T> w) {
        return v.compareTo((T) w) < 0;
    }

    private static <T> boolean more(Comparable<T> v, Comparable<T> w) {
        return v.compareTo((T) w) > 0;
    }
}