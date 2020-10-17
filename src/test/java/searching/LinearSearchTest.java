package searching;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

public class LinearSearchTest {

    private static int N = 100000;

    @Test
    public void linearSearchTest() {

        Integer[] array = new Integer[N];
        for (int i = 0; i < N; i++)
            array[i] =  (int) (1000*Math.random());

        assertThat(LinearSearch.search (array, N, array[100]), is(not(-1)));
    }
}