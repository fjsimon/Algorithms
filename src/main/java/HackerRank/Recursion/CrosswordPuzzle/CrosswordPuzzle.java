package HackerRank.Recursion.CrosswordPuzzle;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CrosswordPuzzle {

    static final int SIZE = 10;
    static List<Space> spaces;

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
        spaces = findSpaces(grid);

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

        int spaceLength = getSpaceLength(r, c, direction);
        if (spaceLength > 1) {
            for (String remainWord : remainWords.stream().filter(remain -> remain.length() == spaceLength).collect(Collectors.toList())) {
                if (canInsert(grid, r, c, direction, remainWord)) {
                    insert(grid, remainWords, r, c, direction, remainWord);
                    char[][] subResult = search(grid, remainWords, r, c, direction + 1);
                    if (subResult != null) {
                        return subResult;
                    }
                    revert(grid, remainWords, r, c, direction, remainWord);
                }
            }
            return null;
        } else {
            return search(grid, remainWords, r, c, direction + 1);
        }
    }

    private static void revert(char[][] grid, Set<String> remainWords, int r, int c, int direction, String remainWord) {

        for (int insertOffset = 0; insertOffset < remainWord.length(); insertOffset++) {
            int insertR = r + (direction % 2) * insertOffset;
            int insertC = c + ((direction + 1) % 2) * insertOffset;
            grid[insertR][insertC] = '-';
        }
        remainWords.add(remainWord);
    }

    private static void insert(char[][] grid, Set<String> remainWords, int r, int c, int direction, String remainWord) {

        for (int insertOffset = 0; insertOffset < remainWord.length(); insertOffset++) {
            int insertR = r + (direction % 2) * insertOffset;
            int insertC = c + ((direction + 1) % 2) * insertOffset;
            grid[insertR][insertC] = remainWord.charAt(insertOffset);
        }
        remainWords.remove(remainWord);
    }

    static int getSpaceLength(int r, int c, int direction) {
        Optional<Space> space = spaces.stream()
                .filter(s -> s.row == r && s.column == c && s.direction == direction)
                .findAny();

        if(space.isPresent()) {
            return space.get().length;
        }else{
            return 0;
        }
    }

    static boolean canInsert(char[][] grid, int r, int c, int direction, String word) {
        return IntStream.range(0, word.length()).allMatch(insertOffset -> {
            int insertR = r + (direction % 2) * insertOffset;
            int insertC = c + ((direction + 1) % 2) * insertOffset;
            return grid[insertR][insertC] == '-' || grid[insertR][insertC] == word.charAt(insertOffset);
        });
    }

    static List<Space> findSpaces(char[][] grid) {

        List<Space> spaceList = new ArrayList();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (grid[i][j] == '-') {
                    int row = i;
                    int column = j;
                    int length = 1;
                    while (++j < SIZE && grid[i][j] == '-')
                        length++;
                    if (length > 1) {
                        spaceList.add(new Space(row, column, 0, length));
                    }
                }
            }
        }

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (grid[j][i] == '-') {
                    int row = j;
                    int column = i;
                    int length = 1;
                    while (++j < SIZE && grid[j][i] == '-')
                        length++;

                    if (length > 1) {
                        spaceList.add(new Space(row, column, 1, length));
                    }
                }

            }
        }
        return spaceList;
    }

    static class Space {

        int row;
        int column;
        int direction;
        int length;

        public Space(int row, int column, int direction, int length) {
            this.row = row;
            this.column = column;
            this.length = length;
            this.direction = direction;
        }
    }
}