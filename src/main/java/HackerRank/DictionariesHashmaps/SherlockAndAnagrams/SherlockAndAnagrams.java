package HackerRank.DictionariesHashmaps.SherlockAndAnagrams;

import java.io.*;
import java.util.*;

public class SherlockAndAnagrams {

    // Complete the sherlockAndAnagrams function below.
    static int sherlockAndAnagrams(String s) {

        HashMap<String, Integer> map = new HashMap<>();
        int result = 0;
        for (int i=0; i<s.length(); i++) {
            for (int j=i+1; j<=s.length(); j++){
                String subString = s.substring(i, j);
                char[] chars = subString.toCharArray();
                Arrays.sort(chars);
                subString = String.valueOf(chars);

                if(map.containsKey(subString)) {
                    int value = map.get(subString);
                    result += value;
                    map.put(subString, value+1);
                } else {
                    map.put(subString, 1);
                }
            }
        }

        return result;
    }


    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String s = scanner.nextLine();

            int result = sherlockAndAnagrams(s);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
