package gfg;

/**
 * https://www.geeksforgeeks.org/frequency-substring-string/
 *
 * Given an input string and a substring. Find the frequency of occurrences of a substring in a given string.
 *
 * Time Complexity : O(M + N)
 */
public class KMP_Algorithm {

    public int search(String pat, String txt) {

        int m = pat.length();
        int n = txt.length();

        int lps[] = new int[m];
        int j = 0;

        compute(pat, m, lps);

        int i = 0;
        int res = 0;
        int next_i = 0;

        while (i < n) {

            if (pat.charAt(j) == txt.charAt(i)) {
                j++;
                i++;
            }

            if (j == m) {
                j = lps[j - 1];
                res++;
                if (lps[j] != 0) {
                    i = ++next_i;
                }
                j = 0;
            } else if (i < n && pat.charAt(j) != txt.charAt(i)) {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i = i + 1;
                }
            }
        }

        return res;
    }


    private void compute(String pat, int m, int lps[]) {

        int len = 0;
        int i = 1;
        lps[0] = 0;

        while (i < m) {

            if (pat.charAt(i) == pat.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = len;
                    i++;
                }
            }
        }

    }

    // Driver program to test above function
    public static void main(String args[]) {

        String txt = "geeksforgeeks";
        String pat = "eeks";
        int ans = new KMP_Algorithm().search(pat, txt);
        System.out.println(ans);
    }
}
