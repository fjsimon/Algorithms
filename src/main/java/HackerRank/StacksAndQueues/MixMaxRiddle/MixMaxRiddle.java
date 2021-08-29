package HackerRank.StacksAndQueues.MixMaxRiddle;

import java.io.*;
import java.util.*;

/**
 * https://www.hackerrank.com/challenges/min-max-riddle/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=stacks-queues
 *
 * https://www.geeksforgeeks.org/find-the-maximum-of-minimums-for-every-window-size-in-a-given-array/
 */

public class MixMaxRiddle {


    // A naive method to find maximum of minimum of all windows of different sizes
    static long[] getMaxOfMin(long[] arr) {

        int n = arr.length;
        long[] result = new long[n];

        for (int k = 1; k <= n; k++) {

            long maxOfMin = Integer.MIN_VALUE;

            for (int i = 0; i <= n - k; i++) {

                long min = arr[i];
                for (int j = 1; j < k; j++) {
                    if (arr[i + j] < min)
                        min = arr[i + j];
                }


                if (min > maxOfMin)
                    maxOfMin = min;
            }

            result[k-1] = maxOfMin;
        }

        return result;
    }


    // An efficient Java program to find maximum of all minimums of windows of different size
    static long[] riddle(long[] arr)
    {

        Stack<Integer> s = new Stack<>();
        int n = arr.length;

        // Arrays to store previous and next smaller
        int left[] = new int[n+1];
        int right[]  = new int[n+1];

        for (int i=0; i<n; i++)
        {
            left[i] = -1;
            right[i] = n;
        }

        for (int i=0; i<n; i++)
        {
            while (!s.empty() && arr[s.peek()] >= arr[i])
                s.pop();

            if (!s.empty())
                left[i] = s.peek();

            s.push(i);
        }

        while (!s.empty())
            s.pop();

        for (int i = n-1 ; i>=0 ; i-- ) {
            while (!s.empty() && arr[s.peek()] >= arr[i])
                s.pop();

            if (!s.empty())
                right[i] = s.peek();

            s.push(i);
        }

        long ans[] = new long[n+1];
        for (int i=0; i<=n; i++)
            ans[i] = 0;

        for (int i=0; i<n; i++)
        {
            // length of the interval
            int len = right[i] - left[i] - 1;

            // arr[i] is a possible answer for this length
            // 'len' interval, check if arr[i] is more than
            // max for 'len'
            ans[len] = Math.max(ans[len], arr[i]);
        }

        // Some entries in ans[] may not be filled yet. Fill
        // them by taking values from right side of ans[]
        for (int i=n-1; i>=1; i--)
            ans[i] = Math.max(ans[i], ans[i+1]);

        long[] result = new long[n];
        for(int i = 0; i < n; i++) {
            result[i] = ans[i+1];
        }
        return result;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        long[] arr = new long[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            long arrItem = Long.parseLong(arrItems[i]);
            arr[i] = arrItem;
        }

        long[] res = riddle(arr);

        for (int i = 0; i < res.length; i++) {
            bufferedWriter.write(String.valueOf(res[i]));

            if (i != res.length - 1) {
                bufferedWriter.write(" ");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
