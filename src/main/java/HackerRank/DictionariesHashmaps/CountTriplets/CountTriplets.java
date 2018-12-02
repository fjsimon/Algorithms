package HackerRank.DictionariesHashmaps.CountTriplets;

import java.io.*;
import java.util.*;

public class CountTriplets {

    // Complete the countTriplets function below.
    static long countTriplets(List<Long> arr, long r) {

        HashMap<Long, Long> map1 = new HashMap();
        HashMap<Long, Long> map2 = new HashMap();
        long count = 0;
        for(int i = arr.size() - 1; i >= 0; i--) {
            long a = arr.get(i);
            if(map2.containsKey(a*r)) {
                count += map2.get(a * r);
            }
            if(map1.containsKey(a*r)) {
                long c = map1.get(a*r);
                map2.put(a, map2.containsKey(a) ? map2.get(a) + c : c);
            }
            map1.put(a, map1.containsKey(a) ? map1.get(a) + 1 : 1L);
        }
        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nr = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(nr[0]);

        long r = Long.parseLong(nr[1]);

        String[] arrItems = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        List<Long> arr = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            long arrItem = Long.parseLong(arrItems[i]);
            arr.add(arrItem);
        }

        long ans = countTriplets(arr, r);

        System.out.println(ans);

        bufferedWriter.write(String.valueOf(ans));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
