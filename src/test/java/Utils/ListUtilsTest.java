package Utils;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ListUtilsTest {

    @Test
    public void removeZerosTest(){

        List<Integer> list = Arrays.asList(109090,2,0,5,0,0,10,8,0,-2000,0,4,0);
        List<Integer> expected = Arrays.asList(199,2,5,1,8,-2,4,0,0,0,0,0,0,0,0,0,0,0,0,0);

        assertEquals(expected, ListUtils.removeZeros(list));
    }

    @Test
    public void removeZerosLambdaTest(){

        List<Integer> list = Arrays.asList(109090,2,0,5,0,0,10,8,0,-2000,0,4,0);
        List<Integer> expected = Arrays.asList(199,2,5,1,8,-2,4,0,0,0,0,0,0,0,0,0,0,0,0,0);

        assertEquals(expected, ListUtils.removeZerosLambda(list));

        list = Arrays.asList(-109090,205,0,5,10,8,0,-2040,0,4,0);
        expected = Arrays.asList(-199,25,5,1,8,-24,4,0,0,0,0,0,0,0,0,0,0,0);

        assertEquals(expected, ListUtils.removeZerosLambda(list));

        list = Arrays.asList(-1,0,-2,0,3,0,4,0,-5,0,6);
        expected = Arrays.asList(-1,-2,3,4,-5,6,0,0,0,0,0);

        assertEquals(expected, ListUtils.removeZerosLambda(list));

    }

}
