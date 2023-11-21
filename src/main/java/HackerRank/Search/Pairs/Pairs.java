package HackerRank.Search.Pairs;

import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;

class Pairs {

    /*
     * Complete the 'pairs' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER k
     *  2. INTEGER_ARRAY arr
     */
    public static int pairs(int k, List<Integer> arr) {

        int count = 0;
        Set<Integer> set = new HashSet<>(arr);
        for (int i : arr) {
            int val = i+k;
            if (set.contains(val))
                count++;
        }
        return count;
    }

//    public static int pairs2(int k, List<Integer> arr) {
//
//        int i,j,result = 0;
//        Collections.sort(arr);
//        for(i = arr.size()-1; i>0; i--) {
//            int v = arr.get(i);
//            j = i-1;
//            while(j >= 0 && v-arr.get(j) <= k) {
//                if(v-arr.get(j) == k) {
//                    result++;
//                }
//                j--;
//            }
//        }
//        return result;
//    }
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

//        int n = Integer.parseInt(firstMultipleInput[0]);

        int k = Integer.parseInt(firstMultipleInput[1]);

        List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        int result = pairs(k, arr);

        System.out.println(result);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}