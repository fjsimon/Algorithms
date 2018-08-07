package HackerRank.CrackingTheCodingInterview.Algorithms;

import java.io.*;
import java.util.*;

public class RecursionDavisStaircase {

    // Solution 4
    static int stepPerms(int n) {
        if(n < 0) return 0;
        else if(n <= 1) return 1;

        int[] paths = {1, 1, 2};
        for(int i = 3; i <= n; i++) {
            int count = paths[2] + paths[1] + paths[0];
            paths[0] = paths[1];
            paths[1] = paths[2];
            paths[2] = count;
        }
        return paths[2];
    }

    // Solution 1
    public int countPathsR(int steps) {
        if(steps < 0) {
            return 0;
        } else if(steps == 0) {
            return 1;
        }
        return countPathsR(steps - 1) + countPathsR(steps - 2) + countPathsR(steps - 3);
    }

    // Solution 2
    public int countPathsMemo(int steps) {
        return countPathsMemo(steps, new int[steps + 1]);
    }

    public static int countPathsMemo(int steps, int[] memo) {
        if(steps < 0) {
            return 0;
        } else if(steps == 0) {
            return 1;
        }
        if(memo[steps] == 0) {
            memo[steps] = countPathsMemo(steps-1, memo) +
                    countPathsMemo(steps-2, memo) +
                    countPathsMemo(steps-3, memo);
        }
        return memo[steps];
    }

    // Solution 3
    public int countPathsDP(int steps) {
        if(steps < 0) {
            return 0;
        } else if(steps == 0) {
            return 1;
        }
        int[] paths = new int[steps + 1];
        paths[0] = 1;
        paths[1] = 1;
        paths[2] = 2;
        for(int i = 3; i <= steps; i++) {
            paths[i] = paths[i - 1] + paths[i - 2] + paths[i - 3];
        }
        return paths[steps];
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int s = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int sItr = 0; sItr < s; sItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int res = stepPerms(n);

            bufferedWriter.write(String.valueOf(res));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}