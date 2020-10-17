package searching;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

public class JumpSearchTest {

    private static int N = 100000;

    @Test
    public void jumpSearchTest() {

        Integer[] array = new Integer[N];
        for (int i = 0; i < N; i++)
            array[i] =  (int) (1000*Math.random());

        // Similar to binary search (sorted array).
        Arrays.sort(array);

        assertThat(JumpSearch.jumpSearch(array, array[100]), is(not(-1)));
    }
}