package Graph.ConnectedNodesProblem;

import java.util.Objects;

public class Node {

    private static final int MAX = 10;
    private static final int MIN = 0;

    private int column;
    private int row;
    private boolean visited;

    Node(int column, int row) {

        this.column = column;
        this.row = row;
        this.visited = false;
    }

    public int getColumn() {

        return this.column;
    }

    public int getRow() {

        return this.row;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public boolean isUpConnected(Node b) {

        return this.getRow() + 1 < MAX && this.getRow() + 1 == b.getRow() && this.getColumn() == b.getColumn();
    }

    public boolean isDownConnected(Node b) {

        return this.getRow() - 1 >= MIN && this.getRow() - 1 == b.getRow() && this.getColumn() == b.getColumn();
    }

    public boolean isRightConnected(Node b) {

        return this.getColumn() + 1 < MAX && this.getColumn() + 1 == b.getColumn() && this.getRow() == b.getRow();
    }

    public boolean isLeftConnected(Node b) {

        return this.getColumn() - 1 >= MIN && this.getColumn() - 1 == b.getColumn() && this.getRow() == b.getRow();
    }

    public String getKey() {

        StringBuilder builder = new StringBuilder();
        return builder.append(column).append(row).toString();
    }

    @Override
    public String toString() {

        return String.format("Node{column=%d, row=%d", column, row);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Node node = (Node) o;
        return column == node.column && row == node.row;
    }

    @Override
    public int hashCode() {
        return Objects.hash(column, row);
    }
}

