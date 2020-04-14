package Codility;

public class Turbulent {

    public int solution(int[] A) {

        return max(A);
    }

    public int max(int[] A) {
        int inc = 1, dec = 1, res = 1;
        for (int i = 1; i < A.length; i++) {
            if (A[i] > A[i - 1]) {
                inc = dec + 1;
                dec = 1;
            } else if (A[i] < A[i - 1]) {
                dec = inc + 1;
                inc = 1;
            } else {
                inc = 1;
                dec = 1;
            }
            res = Math.max(res, Math.max(inc, dec));
        }

        return res;
    }
}
