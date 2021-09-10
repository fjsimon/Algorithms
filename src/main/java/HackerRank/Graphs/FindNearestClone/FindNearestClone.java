package HackerRank.Graphs.FindNearestClone;

import java.io.*;
import java.util.*;

/**
 * https://www.hackerrank.com/challenges/find-the-nearest-clone/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=graphs
 */
public class FindNearestClone {

    static int findShortest(int graphNodes, int[] graphFrom, int[] graphTo, long[] ids, int val) {

        List<Integer>[] map = new ArrayList[graphNodes];
        HashMap<Integer, Integer> distances = new HashMap<>();

        for (int i = 0; i < graphNodes; i++) {
            map[i] = new ArrayList<>();
        }

        for (int i = 0; i < graphFrom.length; i++) {
            map[graphFrom[i] - 1].add(graphTo[i]);
            map[graphTo[i] - 1].add(graphFrom[i]);
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < ids.length; i++) {
            if (ids[i] == val) {
                queue.add(i + 1);
                distances.put(i + 1, 0);
            }
        }

        if (queue.size() < 2)
            return -1;

        HashSet<Integer> visited = new HashSet<>();
        while (!queue.isEmpty()) {
            int current = queue.remove();
            visited.add(current);
            for (int a : map[current - 1]) {
                if (distances.containsKey(a) && !visited.contains(a)) {
                    return distances.get(a) + distances.get(current) + 1;
                } else {
                    queue.add(a);
                    distances.put(a, distances.get(current) + 1);
                }
            }
        }

        return -1;

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] graphNodesEdges = scanner.nextLine().split(" ");
        int graphNodes = Integer.parseInt(graphNodesEdges[0].trim());
        int graphEdges = Integer.parseInt(graphNodesEdges[1].trim());

        int[] graphFrom = new int[graphEdges];
        int[] graphTo = new int[graphEdges];

        for (int i = 0; i < graphEdges; i++) {
            String[] graphFromTo = scanner.nextLine().split(" ");
            graphFrom[i] = Integer.parseInt(graphFromTo[0].trim());
            graphTo[i] = Integer.parseInt(graphFromTo[1].trim());
        }

        long[] ids = new long[graphNodes];

        String[] idsItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < graphNodes; i++) {
            long idsItem = Long.parseLong(idsItems[i]);
            ids[i] = idsItem;
        }

        int val = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int ans = findShortest(graphNodes, graphFrom, graphTo, ids, val);

        bufferedWriter.write(String.valueOf(ans));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
