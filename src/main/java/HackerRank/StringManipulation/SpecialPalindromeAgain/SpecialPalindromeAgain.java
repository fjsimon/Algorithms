package HackerRank.StringManipulation.SpecialPalindromeAgain;

import java.io.*;
import java.util.*;

public class SpecialPalindromeAgain {


    static long substrCount(int length, String s) {

        long count = 0;

        for(int i = 0; i < length; i++) {

            int innerCounter = 1;
            int counterDown = 0;
            int counterUp = 1;

            while(i - innerCounter >= 0 && i + innerCounter < s.length() &&
                    s.charAt(i + innerCounter) == s.charAt(i - 1)) {

                count++;
                innerCounter++;
            }

            while(i - counterDown >= 0 && i + counterUp < s.length() &&
                    s.charAt(i - counterDown) == s.charAt(i) &&
                    s.charAt(i + counterUp) == s.charAt(i)) {

                count++;
                counterDown++;
                counterUp++;
            }
        }

        return count + length;
    }

    // Optimization
    static long substrCountOptim(int length, String s) {

        long counter = 0;

        for(int i = 0; i < length; i++) {

            // if the current symbol is in the middle of palindrome, e.g. aba
            int offset = 1;

            while (i - offset >= 0 && i + offset < length &&
                    s.charAt(i - offset) == s.charAt(i - 1) &&
                    s.charAt(i + offset) == s.charAt(i - 1)) {

                counter++;
                offset++;
            }

            // if this is repeatable characters aa
            int repeats = 0;

            while (i + 1 < length && s.charAt(i) == s.charAt(i + 1)) {

                repeats++;
                i++;
            }
            counter += repeats * (repeats + 1) / 2;
        }

        return counter + length;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String s = scanner.nextLine();

        long result = substrCount(n, s);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();
        bufferedWriter.close();

        scanner.close();
    }
}
