package Sorting;

public class HeapSort{
	
    public static long comparisons = 0;
    public static long exchanges   = 0;
    
    public long getComparisons() {
		return comparisons;
	}

	public void setComparisons(long comparisons) {
		HeapSort.comparisons = comparisons;
	}

	public long getExchanges() {
		return exchanges;
	}

	public void setExchanges(long exchanges) {
		HeapSort.exchanges = exchanges;
	}
    
	// Sort the array a[0..n-1] by the heapsort algorithm.
	public <T> void sort(Comparable<T>[] a) {
		heapsort(a, a.length-1);
	}

	// Sort the array a[0..lastLeaf] by the heapsort algorithm.
	private <T> void heapsort(Comparable<T> [] a, int lastLeaf) {
		// First, turn the array a[0..lastLeaf] into a max-heap.
		buildMaxHeap(a, lastLeaf);

		// Once the array is a max-heap, repeatedly swap the root
		// with the last leaf, putting the largest remaining element
		// in the last leaf's position, declare this last leaf to no
		// longer be in the heap, and then fix up the heap.
		while (lastLeaf > 0) {
			swap(a, 0, lastLeaf);       // swap the root with the last leaf
			lastLeaf--;                 // the last leaf is no longer in the heap
			maxHeapify(a, 0, lastLeaf); // fix up what's left
		}
	}

	
	@SuppressWarnings("unchecked")
	private static <T> boolean less(Comparable<T> v, Comparable<T> w){ 
		comparisons++;
		return v.compareTo((T) w) < 0; 
	}
	
	// Restore the max-heap property.  When this method is called, the max-heap
	// property holds everywhere, except possibly at node i and its children.
	// When this method returns, the max-heap property holds everywhere.
	private <T> void maxHeapify(Comparable<T> [] a, int i, int lastLeaf) {
		int left = leftChild(i);    // index of node i's left child
		int right = rightChild(i);  // index of node i's right child
		int largest;    // will hold the index of the node with the largest element
		// among node i, left, and right

		// Is there a left child and, if so, does the left child have an
		// element larger than node i?
		if (left <= lastLeaf &&  less(a[i], a[left]))
			largest = left;   // yes, so the left child is the largest so far
		else
			largest = i;      // no, so node i is the largest so far

		// Is there a left child and, if so, does the right child have an
		// element larger than the larger of node i and the left child?
		if (right <= lastLeaf && less(a[largest], a[right]))
			largest = right;  // yes, so the right child is the largest

		// If node i holds an element larger than both the left and right
		// children, then the max-heap property already held, and we need do
		// nothing more.  Otherwise, we need to swap node i with the larger
		// of the two children, and then recurse down the heap from the larger child.
		if (largest != i) {
			swap(a, i, largest);
			maxHeapify(a, largest, lastLeaf);
		}
	}

	// Form array a[0..lastLeaf] into a max-heap.
	private <T> void buildMaxHeap(Comparable<T> [] a, int lastLeaf) {
		int lastNonLeaf = (lastLeaf-1) / 2; // nodes lastNonLeaf+1 to lastLeaf are leaves
		for (int j = lastNonLeaf; j >= 0; j--)
			maxHeapify(a, j, lastLeaf);
	}
	
	// Swap two locations i and j in array a.
	private <T> void swap(Comparable<T> [] a, int i, int j) {
		exchanges++;
		Comparable<T> t = a[i]; a[i] = a[j]; a[j] = t;
	}

	// Return the index of the left child of node i.
	private int leftChild(int i) {
		return 2*i + 1;
	}

	// Return the index of the right child of node i.
	private int rightChild(int i) {
		return 2*i + 2;
	}
}