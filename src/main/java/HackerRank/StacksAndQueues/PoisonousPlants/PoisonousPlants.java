package HackerRank.StacksAndQueues.PoisonousPlants;

import java.io.*;
import java.util.*;
import java.util.stream.*;

import static java.util.stream.Collectors.toList;

/**
 * https://www.hackerrank.com/challenges/poisonous-plants/problem?
 * h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=stacks-queues
 */
class Result {

    /*
     * Complete the 'poisonousPlants' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts INTEGER_ARRAY p as parameter.
     */

    public static int poisonousPlants(List<Integer> p) {

        int maxDays = 0;
        Stack<Integer> stack = new Stack<>();
        for (int index = p.size() - 1; index > 0; index--) {
            final int curPlant = p.get(index);
            int currentCount = 0;
            while (!stack.isEmpty() && stack.peek() > curPlant) {
                // top of stack dies
                stack.pop();
                ++currentCount;
            }
            if (currentCount > maxDays) {
                maxDays = currentCount;
            }
            stack.push(curPlant);
        }

        return maxDays;
    }

}

public class PoisonousPlants {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> p = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        int result = Result.poisonousPlants(p);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}