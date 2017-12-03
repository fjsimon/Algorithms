package sorting;


public class MergeSort{
	
	@SuppressWarnings("rawtypes")
	private static Comparable[] aux; // auxiliary array for merges
    public static long comparisons = 0;
    public static long exchanges   = 0;

    public long getComparisons() {
		return comparisons;
	}

	public void setComparisons(long comparisons) {
		MergeSort.comparisons = comparisons;
	}

	public long getExchanges() {
		return exchanges;
	}

	public void setExchanges(long exchanges) {
		MergeSort.exchanges = exchanges;
	}
	
	@SuppressWarnings("unchecked")
	private static <T> boolean less(Comparable<T> v, Comparable<T> w){ 
		comparisons++;
		return v.compareTo((T) w) < 0; 
	}
	
	@SuppressWarnings("unchecked")
	public static <T> void merge(Comparable<T>[] a, int lo, int mid, int hi){ 
		// Merge a[lo..mid] with a[mid+1..hi].
		int i = lo, j = mid+1;
		for (int k = lo; k <= hi; k++) // Copy a[lo..hi] to aux[lo..hi].
			aux[k] = a[k];
		
		for (int k = lo; k <= hi; k++) // Merge back to a[lo..hi].
			if (less(mid,i)) a[k] = aux[j++];
			else if (less(hi,j)) a[k] = aux[i++];
			else if (less(aux[j], aux[i])) a[k] = aux[j++];
			else a[k] = aux[i++];
	}
	
	public <T> void sort(Comparable<T>[] a){
		aux = new Comparable[a.length]; // Allocate space just once.
		sort(a, 0, a.length - 1);
	}
	
	private <T> void sort(Comparable<T>[] a, int lo, int hi){ 
		// Sort a[lo..hi].
		if (hi <= lo) return;
		int mid = lo + (hi - lo)/2;
		sort(a, lo, mid); // Sort left half.
		sort(a, mid+1, hi); // Sort right half.
		merge(a, lo, mid, hi); // Merge results (code on page 271).
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
//        System.out.println("Mergesort:   " + elapsed + " seconds");
//
//        // print statistics
//        System.out.println("Comparisons: " + comparisons);
//        System.out.println("Exchanges: " + exchanges);
//	}
}