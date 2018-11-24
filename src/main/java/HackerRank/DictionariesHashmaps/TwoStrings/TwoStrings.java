package HackerRank.DictionariesHashmaps.TwoStrings;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

public class TwoStrings {

    static String twoStrings(String s1, String s2) {

//        for(char c : "abcdefghijklmnopqrstuvwxyz".toCharArray()) {
//            if(s1.indexOf(c) > -1 && s2.indexOf(c) > -1) {
//                return "YES";
//            }
//        }
//        return "NO";

        if(Pattern.compile("[" + s2 + "]").matcher(s1).find()) {
            return "YES";
        } else {
            return "NO";
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String s1 = scanner.nextLine();

            String s2 = scanner.nextLine();

            String result = twoStrings(s1, s2);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}