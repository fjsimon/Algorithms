package Algorithms.Sorting;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import Sorting.HeapSort;
import Sorting.QuickSort;

public class SortTest {


    @Test
    public void sortTest(){
        Double[] list = getList(100);
        Double[] list2 = list;

        new HeapSort().sort(list);
        new QuickSort().sort(list2);

        assertEquals(list, list2);

//        for(int i=0; i<list.length-1; i++) {
//            assertTrue(list[i] < list[i+1] );
//        }
    }



    private Double[] getList(int n) {
        Double[] a = new Double[n];
        for (int i = 0; i < n; i++) {
            a[i] = Math.random();
        }
        return a;
    }
}
