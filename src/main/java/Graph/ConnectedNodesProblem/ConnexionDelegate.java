package Graph.ConnectedNodesProblem;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ConnexionDelegate {

    public synchronized int findConnexions(Integer count, List<Node> list) {

        while (hasNoVisited(list)) {

            Node node = getNext(list);
            node.setVisited(true);

            List<Node> temp = new ArrayList<Node>();
            temp.add(node);

            int tempCount = findConnexionsRecursion(new Integer(1), temp, list);
            if (tempCount > count){
                count = tempCount;
            }
        }

        return count;
    }

    private int findConnexionsRecursion(Integer count, List<Node> temp, List<Node> list) {

        if (temp.isEmpty()) {
            return count;
        }

        Node node = temp.remove(0);
        count++;

        List<Node> connected = getConnected(node, list);
        temp.addAll(connected);

        return findConnexionsRecursion(count, temp, list);
    }

    private boolean hasNoVisited(List<Node> list){
        return list.stream().anyMatch(e-> !e.isVisited());
    }

    private Node getNext(List<Node> list){
        return list.stream().filter(e-> !e.isVisited()).findFirst().get();
    }

    private List<Node> getConnected(Node node, List<Node> list) {
        return list.stream().filter(n -> isConnected(node, n) && !n.isVisited()).peek(e -> e.setVisited(true)).collect(Collectors.toList());
    }

    private boolean isConnected(Node a, Node b) {

        return a.isUpConnected(b) || a.isDownConnected(b) || a.isRightConnected(b) || a.isLeftConnected(b);
    }
}
