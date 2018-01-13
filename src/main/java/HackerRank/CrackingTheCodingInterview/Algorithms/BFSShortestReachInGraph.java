package HackerRank.CrackingTheCodingInterview.Algorithms;

import java.util.*;

/**
 * https://www.hackerrank.com/challenges/ctci-bfs-shortest-reach/problem
 */
public class BFSShortestReachInGraph {

    private static class Node {
        final int id;
        final List<Node> linkedNodes = new LinkedList<>();

        private Node(int id) {
            this.id = id;
        }

        private void addLinkedNode(Node node) {
            linkedNodes.add(node);
        }

        private List<Node> getLinkedNodes() {
            return linkedNodes;
        }

    }

    private static class NodeLevel {

        final Node node;
        final int level;

        private NodeLevel(Node n, int l){
            this.node = n;
            this.level = l;
        }

        public Node getNode(){
            return node;
        }

        public int getLevel() {
            return level;
        }
    }

    private static int calculateDistance(int N, Node node1, Node node2) {

        if(node1 == null || node2 == null) {
            return -1;
        }

        final int edgeLength = 6;
        final Queue<NodeLevel> queue = new LinkedList<>();
        queue.offer(new NodeLevel(node1, 0));
        final boolean[] visitedNodes = new boolean[N];

        while(!queue.isEmpty()) {
            final NodeLevel tmp = queue.poll();
            if(visitedNodes[tmp.getNode().id]) {
                continue;
            }

            if(tmp.getNode().id == node2.id) {
                return tmp.getLevel() * edgeLength;
            }

            visitedNodes[tmp.getNode().id] = true;
            for(Node n : tmp.getNode().getLinkedNodes()) {
                queue.offer(new NodeLevel(n, tmp.getLevel() + 1));
            }
        }


        return -1;
    }

    public static void main(String[] args) {
        final Scanner in = new Scanner(System.in);

        int queries = in.nextInt();

        for(int i = 0; i < queries; i++) {

            final int N = in.nextInt();
            final int M = in.nextInt();

            final Node[] graphElems = new Node[N];
            for(int j = 0; j < M; j++) {
                final int x = in.nextInt() - 1;
                Node xNode = graphElems[x];
                if(xNode == null) {
                    xNode = new Node(x);
                    graphElems[x] = xNode;
                }

                final int y = in.nextInt() - 1;
                Node yNode = graphElems[y];
                if(yNode == null) {
                    yNode = new Node(y);
                    graphElems[y] = yNode;
                }

                xNode.addLinkedNode(yNode);
                yNode.addLinkedNode(xNode);
            }

            final int S = in.nextInt() - 1;
            for(int k = 0; k < graphElems.length; k++) {
                if(k != S) {
                    int distance = calculateDistance(N, graphElems[k], graphElems[S]);
                    System.out.print(distance + " ");
                }
            }
            System.out.println();
        }
        in.close();
    }
}