package HackerRank.StringManipulation.SherlockAndTheValidString;

import java.io.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SherlockAndTheValidString {

    private static <T extends Object> Map<T, Long> toMap(Collection<T> list) {

        return list.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    private static boolean isValid(String s) {

        if (s == null || s.length() <= 2)
            return true;

        Collection chars = s.chars().mapToObj(e -> (char) e).collect(Collectors.toCollection(ArrayList::new));
        Map<Character, Long> map = toMap(chars);
        Map<Long, Long> occurence_map = toMap(map.values());

        if (occurence_map.keySet().size() > 2) return false;
        if (occurence_map.keySet().size() == 1) return true;

        Iterator<Long> iter2 = occurence_map.keySet().iterator();
        long freq1 = iter2.next();
        long freq2 = iter2.next();

        if (freq1 > freq2) {
            long temp = freq1;
            freq1 = freq2;
            freq2 = temp;
        }

        if (freq1 == 1) {
            return occurence_map.get(freq1) == 1 ? true : false;
        }
        if (freq2 == freq1 + 1) {
            return (occurence_map.get(freq2) == 1) ? true : false;
        }
        return false;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = scanner.nextLine();

        String result = isValid(s) ? "YES" : "NO";

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
