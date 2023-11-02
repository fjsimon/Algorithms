package HackerRank.GreedyAlgorithms.MinimumAbsoluteDifferenceInAnArray;

import java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.stream.*;

import static java.util.stream.Collectors.toList;

public class MinimumAbsoluteDifferenceInAnArray {

    /*
     * Complete the 'minimumAbsoluteDifference' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts INTEGER_ARRAY arr as parameter.
     */

    public static int minimumAbsoluteDifference(List<Integer> arr) {
        int minDifference = Integer.MAX_VALUE;
        Collections.sort(arr);

        for(int i = 0; i < arr.size() -  1; i++){
            minDifference = Math.min(
                    minDifference,
                    Math.min(
                            Math.abs(arr.get(i) - arr.get(i+1)),
                            Math.abs(arr.get(i+1) - arr.get(i))
                    )
            );
        }

        return minDifference;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        bufferedReader.readLine(); //First line

        List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        int result = minimumAbsoluteDifference(arr);

        System.out.println(result);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
