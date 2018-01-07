package HackerRank.CrackingTheCodingInterview.DataStructures;

import java.util.*;

/**
 * https://www.hackerrank.com/challenges/ctci-making-anagrams/problem
 */
public class StringsMakingAnagrams {

    public static int numberNeeded(String first, String second) {

        int count = 0;
        String la = first.replaceAll("\\s", "").toLowerCase();
        String lb = second.replaceAll("\\s", "").toLowerCase();
        String longest, shortest;

        if (la.length() >= lb.length()) {
            longest = la;
            shortest = lb;
        } else {
            longest = lb;
            shortest = la;
        }

        for (int i = 0; i < longest.length(); i++) {
            char c = longest.charAt(i);
            int index = shortest.indexOf(c);
            if (index > -1) {
                shortest = shortest.substring(0, index) + shortest.substring(index + 1);
                count++;
            }
        }

        return (longest.length() - count) + shortest.length();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String a = in.next();
        String b = in.next();
        System.out.println(numberNeeded(a, b));
    }
}
