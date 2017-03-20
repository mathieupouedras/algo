import java.util.Arrays;
import java.util.Map;
import java.util.Stack;


public final class Board {

    private final int[][] blocks;

    public Board(int[][] blocks) {

        this.blocks = new int[blocks.length][blocks.length];
        for (int i = 0; i <blocks.length; i++) {
            System.arraycopy(blocks[i], 0, this.blocks[i], 0, blocks.length);
        }
    }

    public int dimension() {
        return blocks.length;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(dimension());
        stringBuilder.append("\n");
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                stringBuilder.append(String.format("%2d ", blocks[i][j]));
            }
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }

    public int hamming() {
        int bocksOutOfPlace = 0;
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                if (blocks[j][i] != 0) {
                    if (i != getExpectedI(blocks[j][i]) || j != getExpectedJ(blocks[j][i])) {
                        bocksOutOfPlace++;
                    }
                }
            }
        }
        return bocksOutOfPlace;
    }

    public int manhattan() {
        int distance = 0;
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                if (blocks[j][i] != 0) {
                    distance += Math.abs(i - getExpectedI(blocks[j][i])) + Math.abs(j - getExpectedJ(blocks[j][i]));
                }
            }
        }
        return distance;
    }

    int getExpectedJ(int value) {
        return value / dimension();
    }

    int getExpectedI(int value) {
        return value - (getExpectedJ(value) * dimension());
    }

    public boolean isGoal() {
        return hamming() == 0;

    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (other.getClass() != this.getClass()) {
            return false;
        }

        Board that = (Board) other;
        if (that.dimension() != this.dimension()) {
            return false;
        }

        return that.toString().equals(this.toString());
    }

    public Board twin() {
        int[][] twinBlocks = new int[blocks.length][blocks.length];
        for (int i = 0; i <blocks.length; i++) {
            System.arraycopy(blocks[i], 0, twinBlocks[i], 0, blocks.length);
        }

        int i1 = 0;
        int i2 = 0;
        int j1 = 0;
        int j2 = 0;

        int block1 = 0;
        int block2 = 0;

        for (int i = 0; i <blocks.length; i++) {
            for (int j = 0; j <blocks.length; j++) {
                if (blocks[j][i] != 0) {
                    if (block1 == 0) {
                        i1 = i;
                        j1 = j;
                        block1 = blocks[j][i];
                    } else if (block2 == 0) {
                        i2 = i;
                        j2 = j;
                        block2 = blocks[j][i];
                    } else {
                        break;
                    }
                }
            }
        }

        twinBlocks[j1][i1] = block2;
        twinBlocks[j2][i2] = block1;

        return new Board(twinBlocks);
    }

    public Iterable<Board> neighbors() {
        Stack<Board> neighbors = new Stack<>();

        int[][] copy = new int[blocks.length][blocks.length];
        for (int i = 0; i <blocks.length; i++) {
            System.arraycopy(blocks[i], 0,copy[i], 0, blocks.length);
        }

        for (int i = 0; i <blocks.length; i++) {
            for (int j = 0; j <blocks.length; j++) {
                if (blocks[j][i] == 0) {
                    // North
                    if ( (j + 1) <= dimension()) {
                        copy[j][i] = blocks[j+1][i];
                        copy[j+1][i] = 0;
                        neighbors.push(new Board(copy));
                        copy[j][i] = blocks[j][i];
                        copy[j+1][i] = blocks[j+1][i];
                    }
                    if ( (j - 1) >= 0) {
                        copy[j][i] = blocks[j-1][i];
                        copy[j-1][i] = 0;
                        neighbors.push(new Board(copy));
                        copy[j][i] = blocks[j][i];
                        copy[j-1][i] = blocks[j-1][i];
                    }
                    if ( (i + 1) <= dimension()) {
                        copy[j][i] = blocks[j][i + 1];
                        copy[j][i + 1] = 0;
                        neighbors.push(new Board(copy));
                        copy[j][i] = blocks[j][i];
                        copy[j][i + 1] = blocks[j][i + 1];
                    }
                    if ((i -1) >= 0) {
                        copy[j][i] = blocks[j][i - 1];
                        copy[j][i - 1] = 0;
                        neighbors.push(new Board(copy));
                        copy[j][i] = blocks[j][i];
                        copy[j][i - 1] = blocks[j][i - 1];
                    }
                    break;
                }
            }
        }

        return neighbors;
    }
}
