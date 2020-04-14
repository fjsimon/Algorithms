package Algorithms.Sorting;

/*************************************************************************
 *  Compilation:  javac QuickSort.java
 *  Execution:    java QuickSort N
 *
 *  Generate N random real numbers between 0 and 1 and quicksort them.
 *
 *  On average, this quicksort algorithm runs in time proportional to
 *  N log N, independent of the input distribution. The algorithm
 *  guards against the worst-case by randomly shuffling the elements
 *  before sorting. In addition, it uses Sedgewick's partitioning
 *  method which stops on equal keys. This protects against cases
 *  that make many textbook implementations, even randomized ones,
 *  go quadratic (e.g., all keys are the same).
 *
 *************************************************************************/

public class QuickSort {
    public static long comparisons = 0;
    public static long exchanges   = 0;

    public long getComparisons() {
		return comparisons;
	}

	public void setComparisons(long comparisons) {
		QuickSort.comparisons = comparisons;
	}

	public long getExchanges() {
		return exchanges;
	}

	public void setExchanges(long exchanges) {
		QuickSort.exchanges = exchanges;
	}
	
   /***********************************************************************
    *  Quicksort code from Sedgewick 7.1, 7.2.
 * @param <T>
    ***********************************************************************/
    public <T> void sort(Comparable<T>[] a) {
        shuffle(a);                        // to guard against worst-case
        sort(a, 0, a.length - 1);
    }

    // quicksort a[left] to a[right]
    public <T> void sort(Comparable<T>[] a, int left, int right) {
        if (right <= left) return;
        int i = partition(a, left, right);
        sort(a, left, i-1);
        sort(a, i+1, right);
    }

    // partition a[left] to a[right], assumes left < right
    private static <T> int partition(Comparable<T>[] a, int left, int right) {
        int i = left - 1;
        int j = right;
        while (true) {
            while (less(a[++i], a[right]))      // find item on left to swap
                ;                               // a[right] acts as sentinel
            while (less(a[right], a[--j]))      // find item on right to swap
                if (j == left) break;           // don't go out-of-bounds
            if (i >= j) break;                  // check if pointers cross
            exch(a, i, j);                      // swap two elements into place
        }
        exch(a, i, right);                      // swap with partition element
        return i;
    }

    // is v < w ?    
	@SuppressWarnings("unchecked")
	private static <T> boolean less(Comparable<T> v, Comparable<T> w){ 
		comparisons++;
		return v.compareTo((T) w) < 0; 
	}

    // exchange a[i] and a[j]
//    private static void exch(double[] a, int i, int j) {
//        exchanges++;
//        double swap = a[i];
//        a[i] = a[j];
//        a[j] = swap;
//    }

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static <T> void exch(Comparable<T>[] a, int i, int j){ 
		exchanges++;
		Comparable t = a[i]; a[i] = a[j]; a[j] = t; 
	}
	
    // shuffle the array a[]
    private static <T> void shuffle(Comparable<T>[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int r = i + (int) (Math.random() * (N-i));   // between i and N-1
            exch(a, i, r);
        }
    }

//    public static void main(String[] args) {
//        
//        int N = 10000;
//
//        // generate N random real numbers between 0 and 1
//        long start = System.currentTimeMillis();
//        Double[] a = new Double[N];
//        for (int i = 0; i < N; i++)
//            a[i] = Math.random();
//        long stop = System.currentTimeMillis();
//        double elapsed = (stop - start) / 1000.0;
//        System.out.println("Generating input:  " + elapsed + " seconds");
//
//        // sort them
//        start = System.currentTimeMillis();
//        sort(a);
//        stop = System.currentTimeMillis();
//        elapsed = (stop - start) / 1000.0;
//        System.out.println("Quicksort:   " + elapsed + " seconds");
//
//        // print statistics
//        System.out.println("Comparisons: " + comparisons);
//        System.out.println("Exchanges: " + exchanges);
//    }
}