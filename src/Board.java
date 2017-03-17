import java.util.Arrays;


public final class Board {

    private final int[][] blocks;

    public Board(int[][] blocks) {

        this.blocks = new int[blocks.length][blocks.length];
        for (int i = 0; i <blocks.length; i++) {
            for (int j = 0; j <blocks.length; j++) {
                this.blocks[i][j] = blocks[i][j];
            }
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
                if (blocks[i][j] != (i * dimension()) + j) {
                    bocksOutOfPlace++;
                }
            }
        }
        return bocksOutOfPlace;
    }
    public int manhattan() {
        int distance = 0;
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                distance += Math.abs(blocks[i][j] - ((i * dimension()) + j));
            }
        }

        return distance;
    }

}
