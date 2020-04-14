package Algorithms.Sorting;

public class Selection{
	
    public static long comparisons = 0;
    public static long exchanges   = 0;
    
    public long getComparisons() {
		return comparisons;
	}

	public void setComparisons(long comparisons) {
		Selection.comparisons = comparisons;
	}

	public long getExchanges() {
		return exchanges;
	}

	public void setExchanges(long exchanges) {
		Selection.exchanges = exchanges;
	}
	
	public <T> void sort(Comparable<T>[] a){ 
		// Sort a[] into increasing order.
		int N = a.length; // array length
		for (int i = 0; i < N; i++){ 
			// Exchange a[i] with smallest entry in a[i+1...N).
			int min = i; // index of minimal entr.
			for (int j = i+1; j < N; j++)
				if (less(a[j], a[min])) min = j;
			exch(a, i, min);
		}
	}
	
	@SuppressWarnings("unchecked")
	private static <T> boolean less(Comparable<T> v, Comparable<T> w){ 
		comparisons++;
		return v.compareTo((T) w) < 0; 
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static <T> void exch(Comparable<T>[] a, int i, int j){ 
		exchanges++;
		Comparable t = a[i]; a[i] = a[j]; a[j] = t; 
	}
	

	
	public static <T> boolean isSorted(Comparable<T>[] a){ 
		// Test whether the array entries are in order.
		for (int i = 1; i < a.length; i++)
			if (less(a[i], a[i-1])) return false;
		return true;
	}
	
//	public static void main(String[] args){ 
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
//        
//        // sort them
//        start = System.currentTimeMillis();
//        sort(a);
//        stop = System.currentTimeMillis();
//        elapsed = (stop - start) / 1000.0;
//        System.out.println("Selection:   " + elapsed + " seconds");
//
//        // print statistics
//        System.out.println("Comparisons: " + comparisons);
//        System.out.println("Exchanges: " + exchanges);
//
//	}
}