package HackerRank.DictionariesHashmaps;

import java.util.*;

public class RansomNote {
    Map<String, Integer> magazineMap;
    Map<String, Integer> noteMap;

    public RansomNote(String magazine, String note) {
        magazineMap = new HashMap<>();
        noteMap = new HashMap<>();

        String[] magazineWords = magazine.split("\\s+");
        String[] noteWords = note.split("\\s+");

        for (String m : magazineWords) {

            if(magazineMap.containsKey(m)) {
                magazineMap.put(m, magazineMap.get(m) + 1);
            } else {
                magazineMap.put(m, 1);
            }
        }

        for (String n : noteWords) {

            if(noteMap.containsKey(n)) {
                noteMap.put(n, noteMap.get(n) + 1);
            } else {
                noteMap.put(n, 1);
            }
        }
    }

    public boolean solve() {
        if (magazineMap.size() < noteMap.size()) {
            return false;
        } else {
            for (String key : noteMap.keySet()) {
                if (!magazineMap.containsKey(key)) {
                    return false;
                } else if (noteMap.get(key) > magazineMap.get(key)) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int n = scanner.nextInt();

        // Eat whitespace to beginning of next line
        scanner.nextLine();

        RansomNote s = new RansomNote(scanner.nextLine(), scanner.nextLine());
        scanner.close();

        boolean answer = s.solve();
        if(answer) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }
}
