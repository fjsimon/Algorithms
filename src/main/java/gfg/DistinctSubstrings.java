package gfg;

import java.util.HashSet;

/**
 * https://www.geeksforgeeks.org/count-number-of-distinct-substring-in-a-string/
 * <p>
 * Given a string, count all distinct substrings of the given string.
 */
public class DistinctSubstrings {


    private static void printSubStrings(String s) {

        HashSet<String> us = new HashSet<>();

        for (int i = 0; i < s.length(); ++i) {

            String ss = "";
            for (int j = i; j < s.length(); ++j) {
                ss = ss + s.charAt(j);
                us.add(ss);
            }
        }

        us.stream().forEach(System.out::println);
    }

    public static void main(String... args) {

        String str = "aaabc";
        printSubStrings(str);
    }
}
