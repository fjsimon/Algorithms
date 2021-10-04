package HackerRank.Graphs.Matrix;

import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;

/**
 * https://www.hackerrank.com/challenges/matrix/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=graphs
 */
class Result {

    public static int minTime(List<List<Integer>> roads, List<Integer> machines) {

        Collections.sort(roads, (o1, o2) -> o2.get(2) - o1.get(2));

        int ans = 0;

        Map<Integer, Integer> idToIndex = new HashMap<>();
        List<Integer> relation = new ArrayList<>();
        Set<Integer> machineSet = new HashSet<>();

        for (int machine : machines) {
            machineSet.add(relation.size());
            idToIndex.put(machine, relation.size());
            relation.add(relation.size());
        }


        for (List<Integer> road : roads) {
            int p1 = getParent(road.get(0), idToIndex, relation);
            int p2 = getParent(road.get(1), idToIndex, relation);

            if (machineSet.contains(p1) && machineSet.contains(p2)) {
                ans += road.get(2);
                continue;
            }

            if (machineSet.contains(p1)) {
                relation.set(p2, p1);
            } else {
                relation.set(p1, p2);
            }

        }

        return ans;

    }

    private static int getParent(Integer node, Map<Integer, Integer> idToIndex, List<Integer> relation) {

        if (!idToIndex.containsKey(node)) {
            idToIndex.put(node, relation.size());
            relation.add(relation.size());
        }

        int p = idToIndex.get(node);

        while (p != relation.get(p)) {
            p = relation.get(p);
        }

        return p;
    }

}

public class Matrix {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");
        int n = Integer.parseInt(firstMultipleInput[0]);
        int k = Integer.parseInt(firstMultipleInput[1]);
        List<List<Integer>> roads = new ArrayList<>();
        IntStream.range(0, n - 1).forEach(i -> {
            try {
                roads.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        List<Integer> machines = IntStream.range(0, k).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }}).map(String::trim).map(Integer::parseInt).collect(toList());

        int result = Result.minTime(roads, machines);
        System.out.println(result);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();
        bufferedReader.close();
        bufferedWriter.close();
    }
}