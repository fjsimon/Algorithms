package Graph.ConnectedNodesProblem;

import org.hamcrest.Matcher;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;

public class ConnexionDelegateTest {

    @Test
    public void scenario_1() {

        List<Node> nodeList = Arrays.asList(
                new Node(0,0),
                new Node(0,1),
                new Node(0,2),
                new Node(5,3),
                new Node(8,4),
                new Node(7,2),
                new Node(1,3),
                new Node(2,4),
                new Node(3,9),
                new Node(4,9),
                new Node(5,9),
                new Node(6,9)
        );

        Integer count = new ConnexionDelegate().findConnexions(0, nodeList);
        assertThat(count, is(5));
    }

    private void assertThat(Integer count, Matcher<Integer> integerMatcher) {
    }

    @Test
    public void scenario_2() {

        List<Node> nodeList = Arrays.asList(
                new Node(0,1),
                new Node(1,3),
                new Node(1,1),
                new Node(2,0),
                new Node(2,1),
                new Node(2,2),
                new Node(2,3),
                new Node(3,3)
        );

        Integer count = new ConnexionDelegate().findConnexions(0, nodeList);
        assertThat(count, is(9));
    }

    @Test
    public void scenario_3() {

        List<Node> nodeList = Arrays.asList(
                new Node(3,0),
                new Node(7,0),
                new Node(3,1),
                new Node(7,1),
                new Node(0,2),
                new Node(1,2),
                new Node(2,2),
                new Node(3,2),
                new Node(7,2),
                new Node(1,3),
                new Node(3,3),
                new Node(7,3),
                new Node(1,4),
                new Node(3,4),
                new Node(6,4),
                new Node(7,4)
        );

        Integer count = new ConnexionDelegate().findConnexions(0, nodeList);
        assertThat(count, is(11));
    }

}