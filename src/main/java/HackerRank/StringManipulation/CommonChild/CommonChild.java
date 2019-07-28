package HackerRank.StringManipulation.CommonChild;

import java.io.*;
import java.util.*;

public class CommonChild {

    // Complete the commonChild function below.
    static int commonChild(String s1, String s2) {
        int [][] lcs_matrix = new int[s1.length()+1][s2.length()+1];
        for(int i = 0; i < s1.length()+1; i++){
            lcs_matrix[i][0] = 0;
        }

        for(int j = 0; j < s2.length()+1; j++) {
            lcs_matrix[0][j] = 0;
        }
        for(int i = 1; i <= s1.length(); i++) {
            for(int j = 1; j <= s2.length(); j++) {
                if(s1.charAt(i-1) == s2.charAt(j-1)) {
                    lcs_matrix[i][j] = lcs_matrix[i-1][j-1] + 1;
                } else {
                    lcs_matrix[i][j] = Math.max(lcs_matrix[i][j-1], lcs_matrix[i-1][j]);
                }
            }
        }

        return lcs_matrix[s1.length()][s2.length()];
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s1 = scanner.nextLine();

        String s2 = scanner.nextLine();

        int result = commonChild(s1, s2);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
