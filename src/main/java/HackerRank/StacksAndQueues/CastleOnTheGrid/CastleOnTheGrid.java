package HackerRank.StacksAndQueues.CastleOnTheGrid;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

/**
 *
 * https://www.hackerrank.com/challenges/castle-on-the-grid/problem?h_l=interview&isFullScreen=false&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=stacks-queues
 *
 */
class Result {

    static final int[] X_OFFSETS = { -1, 0, 1, 0 };
    static final int[] Y_OFFSETS = { 0, 1, 0, -1 };


    public static int minimumMoves(List<String> grid, int startX, int startY, int goalX, int goalY) {
        if (startX == goalX && startY == goalY) {
            return 0;
        }

        int size = grid.size();

        int[][] moves = new int[size][size];
        IntStream.range(0, size).forEach(x -> Arrays.fill(moves[x], -1));

        moves[startX][startY] = 0;
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(startX, startY));

        while (true) {
            Point head = queue.poll();
            for (int i = 0; i < X_OFFSETS.length; i++) {
                int nextX = head.x;
                int nextY = head.y;

                while (isOpen(grid, nextX + X_OFFSETS[i], nextY + Y_OFFSETS[i])) {
                    nextX += X_OFFSETS[i];
                    nextY += Y_OFFSETS[i];

                    if (nextX == goalX && nextY == goalY) {
                        return moves[head.x][head.y] + 1;
                    }

                    if (moves[nextX][nextY] < 0) {
                        moves[nextX][nextY] = moves[head.x][head.y] + 1;
                        queue.offer(new Point(nextX, nextY));
                    }
                }
            }
        }
    }

    static boolean isOpen(List<String> grid, int x, int y) {

        return x >= 0 && x < grid.size() && y >= 0 && y < grid.size() && grid.get(x).charAt(y) == '.';
    }
}

class Point {
    int x;
    int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class CastleOnTheGrid {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<String> grid = IntStream.range(0, n).mapToObj(i -> {
            try {
                return bufferedReader.readLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }).collect(toList());


        String[] firstMultipleInput = bufferedReader.readLine().
                replaceAll("\\s+$", "").split(" ");

        int startX = Integer.parseInt(firstMultipleInput[0]);

        int startY = Integer.parseInt(firstMultipleInput[1]);

        int goalX = Integer.parseInt(firstMultipleInput[2]);

        int goalY = Integer.parseInt(firstMultipleInput[3]);

        int result = Result.minimumMoves(grid, startX, startY, goalX, goalY);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
