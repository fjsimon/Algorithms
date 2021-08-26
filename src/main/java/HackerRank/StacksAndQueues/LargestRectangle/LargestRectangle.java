package HackerRank.StacksAndQueues.LargestRectangle;

import java.io.*;
import java.util.*;
import java.util.stream.*;

import static java.util.stream.Collectors.toList;

/**
 * https://www.hackerrank.com/challenges/largest-rectangle/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=stacks-queues
 */
class Result {

    public static long largestRectangle(List<Integer> h) {

        if (h == null || h.size() == 0)
            return 0;

        Stack<Integer> stack = new Stack<>();
        int max = 0, i = 0;

        while (i < h.size()) {

            if (stack.isEmpty() || h.get(i) >= h.get(stack.peek())) {

                stack.push(i);
                i++;
            } else {
                max = getMax(h, stack, max, i);
            }
        }

        while (!stack.isEmpty()) {
            max = getMax(h, stack, max, i);
        }

        return max;
    }

    private static int getMax(List<Integer> h, Stack<Integer> stack, int max, int i) {

        int p = stack.pop();
        int height = h.get(p);
        int width = stack.isEmpty() ? i : i - stack.peek() - 1;
        return Math.max(height * width, max);
    }

}

public class LargestRectangle {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> h = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        long result = Result.largestRectangle(h);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
