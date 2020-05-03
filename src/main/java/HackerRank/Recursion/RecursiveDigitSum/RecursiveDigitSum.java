package HackerRank.Recursion.RecursiveDigitSum;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class RecursiveDigitSum {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nk = scanner.nextLine().split(" ");

        String n = nk[0];

        int k = Integer.parseInt(nk[1]);

        int result = superDigit(n, k);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }

    static int superDigit(String n, int k) {

        n = n.chars().mapToLong(Character::getNumericValue).sum() * k + "";
        return (n.length() > 1) ? superDigit(n, 1) : Character.getNumericValue(n.charAt(0));
    }
}