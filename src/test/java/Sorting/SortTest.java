package Sorting;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SortTest {

    private static int N = 100000;

    @Test
    public void test(){

        QuickSort quicksort = new QuickSort();
        MergeSort mergesort = new MergeSort();
        Selection selection = new Selection();
        HeapSort heapsort = new HeapSort();

        // generate N random real numbers between 0 and 1
        long start = System.currentTimeMillis();
        Double[] a = new Double[N];
        for (int i = 0; i < N; i++)
            a[i] = Math.random();
        long stop = System.currentTimeMillis();
        double elapsed = (stop - start) / 1000.0;
        System.out.println("Generating input:  " + elapsed + " seconds");

        start = System.currentTimeMillis();
        quicksort.sort(a);
        stop = System.currentTimeMillis();
        elapsed = (stop - start) / 1000.0;
        System.out.println("Quicksort:   " + elapsed + " seconds");

        start = System.currentTimeMillis();
        mergesort.sort(a);
        stop = System.currentTimeMillis();
        elapsed = (stop - start) / 1000.0;
        System.out.println("Mergesort:   " + elapsed + " seconds");

        // sort them
        start = System.currentTimeMillis();
        selection.sort(a);
        stop = System.currentTimeMillis();
        elapsed = (stop - start) / 1000.0;
        System.out.println("Selection:   " + elapsed + " seconds");

        // sort them
        start = System.currentTimeMillis();
        heapsort.sort(a);
        stop = System.currentTimeMillis();
        elapsed = (stop - start) / 1000.0;
        System.out.println("Heapsort:   " + elapsed + " seconds");

        // print statistics
        System.out.println("Quicksort Comparisons: " + quicksort.getComparisons());
        System.out.println("Quicksort Exchanges: " + quicksort.getExchanges());
        System.out.println("Mergesort Comparisons: " + mergesort.getComparisons());
        System.out.println("Mergesort Exchanges: " + mergesort.getExchanges());
        System.out.println("Selection Comparisons: " + selection.getComparisons());
        System.out.println("Selection Exchanges: " + selection.getExchanges());
        System.out.println("Heapsort Comparisons: " + heapsort.getComparisons());
        System.out.println("Heapsort Exchanges: " + heapsort.getExchanges());


        assertTrue(heapsort.getComparisons() > 0);
        assertTrue(heapsort.getExchanges() > 0);

        assertTrue(quicksort.getComparisons() < mergesort.getComparisons());
        assertTrue(quicksort.getComparisons() < selection.getComparisons());
        assertTrue(quicksort.getComparisons() < heapsort.getComparisons());

    }

}