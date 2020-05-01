package HackerRank.Recursion.CrosswordPuzzle;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CrosswordPuzzle {

    static final int SIZE = 10;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        char[][] grid = new char[SIZE][SIZE];
        for (int r = 0; r < SIZE; r++) {
            String line = sc.next();
            for (int c = 0; c < SIZE; c++) {
                grid[r][c] = line.charAt(c);
            }
        }
        String[] words = sc.next().split(";");

        char[][] solution = solve(grid, words);
        IntStream.range(0, SIZE).forEach(r -> System.out.println(String.valueOf(solution[r])));

        sc.close();
    }

    static char[][] solve(char[][] grid, String[] words) {
        return search(grid, Arrays.stream(words).collect(Collectors.toSet()), 0, 0, 0);
    }

    static char[][] search(char[][] grid, Set<String> remainWords, int r, int c, int direction) {
        if (r == SIZE) {
            return grid;
        }
        if (c == SIZE) {
            return search(grid, remainWords, r + 1, 0, 0);
        }
        if (direction == 2) {
            return search(grid, remainWords, r, c + 1, 0);
        }

        int insertLength = getInsertLength(grid, r, c, direction);
        if (insertLength > 1) {
            for (String remainWord : remainWords.stream().filter(rw -> rw.length() == insertLength).collect(Collectors.toList())) {
                if (canInsert(grid, r, c, direction, remainWord)) {
                    insert(grid, remainWords, r, c, direction, insertLength, remainWord);
                    char[][] subResult = search(grid, remainWords, r, c, direction + 1);
                    if (subResult != null) {
                        return subResult;
                    }
                    revert(grid, remainWords, r, c, direction, insertLength, remainWord);
                }
            }
            return null;
        } else {
            return search(grid, remainWords, r, c, direction + 1);
        }
    }

    private static void revert(char[][] grid, Set<String> remainWords, int r, int c, int direction, int insertLength, String remainWord) {

        for (int insertOffset = 0; insertOffset < insertLength; insertOffset++) {
            int insertR = r + (direction % 2) * insertOffset;
            int insertC = c + ((direction + 1) % 2) * insertOffset;
            grid[insertR][insertC] = '-';
        }
        remainWords.add(remainWord);
    }

    private static void insert(char[][] grid, Set<String> remainWords, int r, int c, int direction, int insertLength, String remainWord) {

        for (int insertOffset = 0; insertOffset < insertLength; insertOffset++) {
            int insertR = r + (direction % 2) * insertOffset;
            int insertC = c + ((direction + 1) % 2) * insertOffset;
            grid[insertR][insertC] = remainWord.charAt(insertOffset);
        }
        remainWords.remove(remainWord);
    }

    static int getInsertLength(char[][] grid, int r, int c, int direction) {
        int prevR = r - (direction % 2);
        int prevC = c - ((direction + 1) % 2);

        if (prevR >= 0 && prevR < SIZE && prevC >= 0 && prevC < SIZE && grid[prevR][prevC] != '+') {
            return 0;
        }

        int insertLength = 0;
        while (r >= 0 && r < SIZE && c >= 0 && c < SIZE && grid[r][c] != '+') {
            insertLength++;

            r += (direction % 2);
            c += ((direction + 1) % 2);
        }
        return insertLength;
    }

    static boolean canInsert(char[][] grid, int r, int c, int direction, String word) {
        return IntStream.range(0, word.length()).allMatch(insertOffset -> {
            int insertR = r + (direction % 2) * insertOffset;
            int insertC = c + ((direction + 1) % 2) * insertOffset;
            return grid[insertR][insertC] == '-' || grid[insertR][insertC] == word.charAt(insertOffset);
        });
    }
}