package searching;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

public class BinarySearchTest {

    private static int N = 1000;

    @Test
    public void binarySearchTest() {

        Integer[] array = new Integer[N];
        for (int i = 0; i < N; i++)
            array[i] =  (int) (1000*Math.random());

        Arrays.sort(array);

        assertThat(BinarySearch.indexOf(array, array[100]), is(not(-1)));
    }

    @Test
    public void binarySearchIterativelyTest() {

        Integer[] array = new Integer[N];
        for (int i = 0; i < N; i++)
            array[i] =  (int) (1000*Math.random());

        Arrays.sort(array);

        assertThat(BinarySearch.binarySearchIteratively(array, array[100], 0, N-1), is(not(-1)));
    }

    @Test
    public void binarySearchRecursivelyTest() {

        Integer[] array = new Integer[N];
        for (int i = 0; i < N; i++)
            array[i] =  (int) (1000*Math.random());

        Arrays.sort(array);

        assertThat(BinarySearch.binarySearchRecursively(array, array[100], 0, N-1), is(not(-1)));
    }
}