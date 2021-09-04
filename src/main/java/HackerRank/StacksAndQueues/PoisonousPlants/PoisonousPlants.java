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

    static int poisonousPlants2(List<Integer> p) {

        List<List<Integer>> allstacks = new ArrayList<>();

        int flag = p.get(0);
        List<Integer> ll = new ArrayList<>();
        ll.add(p.get(0));
        allstacks.add(ll);

        for (int j = 1; j < p.size(); j++) {
            int i = p.get(j);
            if (i <= flag)
                ll.add(i);
            else {
                ll = new ArrayList<>();
                ll.add(i);
                allstacks.add(ll);
            }
            flag = i;
        }

        int day = 0;
        boolean index;

        do {
            day++;
            index = false;
            for (int i = allstacks.size() - 1; i > 0; i--) {
                List<Integer> current = allstacks.get(i);
                current.remove(0);

                List<Integer> before = allstacks.get(i - 1);

                if (current.size() == 0)
                    allstacks.remove(i);
                else if (current.get(0) <= before.get(before.size() - 1)) {
                    before.addAll(current);
                    allstacks.remove(i);
                }
                index = true;
            }
        } while (index);

        return day - 1;

    }

    /**
     * Passed
     */
    public static int poisonousPlants3(List<Integer> plants) {
        int maxDays = 0, day = 0;
        Stack<int[]> stack = new Stack<>();

        for (int pes : plants) {
            day = 0;
            while (!stack.isEmpty() && stack.peek()[0] >= pes) {
                day = Math.max(day, stack.pop()[1]);
            }
            if (!stack.isEmpty()) {
                day++;
            } else {
                day = 0;
            }
            maxDays = Math.max(day, maxDays);
            stack.push(new int[]{pes, day});
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

        int result = Result.poisonousPlants3(p);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}