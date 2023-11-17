package HackerRank.Search.SwapNodes;

import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class SwapNodes {

    /*
     * Complete the 'swapNodes' function below.
     *
     * The function is expected to return a 2D_INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. 2D_INTEGER_ARRAY indexes
     *  2. INTEGER_ARRAY queries
     */
    public static List<List<Integer>> swapNodes(List<List<Integer>> indexes, List<Integer> queries) {

        Node root = buildTree(indexes, 1, 1);
        List<List<Integer>> result = new ArrayList<>();

        if(root == null)
            return result;

        int maxDepth = getMaxDepth(root);

        for(int query : queries){
            Set<Integer> depths = getSwapDepths(query, maxDepth);
            List<Integer> currentResult = new ArrayList<>();
            swapTraverse(root, depths, currentResult);
            result.add(currentResult);
        }

        return result;
    }

    public static Node buildTree(List<List<Integer>> indexes, int index, int depth){

        if(index == -1)
            return null;

        Node node = new Node(index, depth);
        node.left = buildTree(indexes,
                indexes.get(index - 1).get(0),
                depth + 1);
        node.right = buildTree(indexes,
                indexes.get(index - 1).get(1),
                depth + 1);

        return node;
    }

    public static int getMaxDepth(Node node){

        if(node == null)
            return 0;

        return 1 + Math.max(
                getMaxDepth(node.left),
                getMaxDepth(node.right)
        );
    }

    public static Set<Integer> getSwapDepths(int query, int maxDepth){

        Set<Integer> set = new HashSet<>();
        int value = query;

        while(value < maxDepth) {
            set.add(value);
            value += query;
        }

        return set;
    }

    public static void swapTraverse(Node node, Set<Integer> depths, List<Integer> result){

        if(node == null)
            return;

        if(depths.contains(node.depth))
            swap(node);

        swapTraverse(node.left, depths, result);
        result.add(node.value);
        swapTraverse(node.right, depths, result);
    }

    public static void swap(Node node){

        Node tmp = node.left;
        node.left = node.right;
        node.right = tmp;
    }

    public static class Node{

        public int value;
        public int depth;
        public Node left;
        public Node right;

        public Node(int value, int depth) {

            this.value = value;
            this.depth = depth;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> indexes = new ArrayList<>();

        IntStream.range(0, n).forEach(i -> {
            try {
                indexes.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int queriesCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> queries = IntStream.range(0, queriesCount).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine().replaceAll("\\s+$", "");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        List<List<Integer>> result = swapNodes(indexes, queries);

        result.stream()
                .map(
                        r -> r.stream()
                                .map(Object::toString)
                                .collect(joining(" "))
                )
                .map(r -> r + "\n")
                .collect(toList())
                .forEach(e -> {
                    try {
                        bufferedWriter.write(e);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
