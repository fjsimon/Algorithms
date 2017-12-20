package searching;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

public class JumpSearchTest {

    private static int N = 100000;

    @Test
    public void linearSearchTest() {

        Integer[] array = new Integer[N];
        for (int i = 0; i < N; i++)
            array[i] =  (int) (1000*Math.random());

        // Similar to binary search. Arra
        Arrays.sort(array);

        assertThat(JumpSearch.jumpSearch(array, array[100]), is(not(-1)));
    }
}