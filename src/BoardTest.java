import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BoardTest {

    @Test
    public void should_return_dimension_2() {
        int[][] blocks = new int[2][2];

        Board board = new Board(blocks);

        assertThat(board.dimension(), is(2));
    }

    @Test
    public void should_toString() {
        int dimension = 2;
        int[][] blocks = new int[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                blocks[j][i] = i + j * dimension;
            }
        }

        Board board = new Board(blocks);

        StringBuilder expectedString = new StringBuilder();
        expectedString.append(dimension);
        expectedString.append("\n");
        expectedString.append(" 0  1 ");
        expectedString.append("\n");
        expectedString.append(" 2  3 ");
        expectedString.append("\n");

        assertThat(board.toString(), is(expectedString.toString()));
    }

    @Test
    public void should_be_immutable() {
        int dimension = 2;
        int[][] blocks = new int[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                blocks[i][j] = i + j;
            }
        }

        Board board = new Board(blocks);

        String before = board.toString();
        blocks[0][0] = 8;
        String after = board.toString();

        assertThat(after, is(before));
    }

    @Test
    public void should_hamming_2() {
        int dimension = 2;
        int[][] blocks = new int[dimension][dimension];
        blocks[0][0] = 0;
        blocks[0][1] = 3;
        blocks[1][0] = 2;
        blocks[1][1] = 1;

        Board board = new Board(blocks);

        System.out.println(board);

        assertThat(board.hamming(), is(2));
    }

    @Test
    public void should_hamming_3() {
        int dimension = 2;
        int[][] blocks = new int[dimension][dimension];
        blocks[0][0] = 0;
        blocks[0][1] = 2;
        blocks[1][0] = 3;
        blocks[1][1] = 1;

        Board board = new Board(blocks);

        assertThat(board.hamming(), is(3));
    }

    @Test
    public void should_hamming_0() {
        int dimension = 2;
        int[][] blocks = new int[dimension][dimension];
        blocks[0][0] = 0;
        blocks[0][1] = 1;
        blocks[1][0] = 2;
        blocks[1][1] = 3;

        Board board = new Board(blocks);

        assertThat(board.hamming(), is(0));
    }

    @Test public void shoud_manathan_4() {
        int dimension = 2;
        int[][] blocks = new int[dimension][dimension];
        blocks[0][0] = 0;
        blocks[0][1] = 2;
        blocks[1][0] = 1;
        blocks[1][1] = 3;

        Board board = new Board(blocks);

        /**
         *   0  2   =>   0  1
         *   1  3        2  3
         */
        assertThat(board.manhattan(), is(4));
    }

    @Test public void shoud_manathan_2() {
        int dimension = 2;
        int[][] blocks = new int[dimension][dimension];
        blocks[0][0] = 0;
        blocks[0][1] = 1;
        blocks[1][0] = 3;
        blocks[1][1] = 2;

        Board board = new Board(blocks);

        /**
         *   0  1   =>   0  1
         *   3  2        2  3
         */
        assertThat(board.manhattan(), is(2));
    }

    @Test public void shoud_manathan_0() {
        int dimension = 2;
        int[][] blocks = new int[dimension][dimension];
        blocks[0][0] = 0;
        blocks[0][1] = 1;
        blocks[1][0] = 2;
        blocks[1][1] = 3;

        Board board = new Board(blocks);

        /**
         *   0  1   =>   0  1
         *   2  3        2  3
         */
        assertThat(board.manhattan(), is(0));
    }



    @Test
    public void sould_getI_2() {
        int dimension = 3;
        int[][] blocks = new int[dimension][dimension];
        Board board = new Board(blocks);

        assertThat(board.getExpectedJ(8), is(2));
        assertThat(board.getExpectedI(8), is(2));
    }

    @Test
    public void sould_getI_0() {
        int dimension = 3;
        int[][] blocks = new int[dimension][dimension];
        Board board = new Board(blocks);

        assertThat(board.getExpectedJ(0), is(0));
        assertThat(board.getExpectedI(0), is(0));
    }

    @Test
    public void sould_getI_0_J_1() {
        int dimension = 3;
        int[][] blocks = new int[dimension][dimension];
        Board board = new Board(blocks);

        assertThat(board.getExpectedJ(3), is(1));
        assertThat(board.getExpectedI(3), is(0));
    }

    @Test
    public void should_getExpected() {
        int dimension = 2;
        int[][] blocks = new int[dimension][dimension];

        Board board = new Board(blocks);

        assertThat(board.getExpectedI(0), is(0));
        assertThat(board.getExpectedJ(0), is(0));

        assertThat(board.getExpectedI(1), is(1));
        assertThat(board.getExpectedJ(1), is(0));

        assertThat(board.getExpectedI(2), is(0));
        assertThat(board.getExpectedJ(2), is(1));

        assertThat(board.getExpectedI(3), is(1));
        assertThat(board.getExpectedJ(3), is(1));
    }

    @Test
    public void isGoal_true() {
        int dimension = 3;
        int[][] blocks = new int[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                blocks[j][i] = i + (j * dimension);
            }
        }

        Board board = new Board(blocks);

        assertThat(board.isGoal(), is(true));
    }

    @Test
    public void isGoal_false() {
        int dimension = 3;
        int[][] blocks = new int[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                blocks[j][i] = i + (j * dimension);
            }
        }

        blocks[2][2] = 7;
        blocks[2][1] = 8;

        Board board = new Board(blocks);

        assertThat(board.isGoal(), is(false));
    }

    @Test
    public void equals_true() {
        int dimension = 3;
        int[][] blocks = new int[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                blocks[j][i] = i + (j * dimension);
            }
        }
        Board board = new Board(blocks);

        Board board2 = new Board(blocks);

        assertThat(board.equals(board2), is(true));
    }

    @Test
    public void equals_false() {
        int dimension = 3;
        int[][] blocks = new int[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                blocks[j][i] = i + (j * dimension);
            }
        }
        Board board = new Board(blocks);

        blocks[2][2] = 7;
        blocks[2][1] = 8;
        Board board2 = new Board(blocks);

        assertThat(board.equals(board2), is(false));
    }

    @Test
    public void equals_false_with_different_out_of_place() {
        int dimension = 3;
        int[][] blocks = new int[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                blocks[j][i] = i + (j * dimension);
            }
        }

        blocks[2][2] = 7;
        blocks[2][1] = 8;
        Board board = new Board(blocks);


        blocks[2][2] = 8;
        blocks[2][1] = 7;

        blocks[0][1] = 2;
        blocks[0][2] = 1;

        Board board2 = new Board(blocks);

        assertThat(board.equals(board2), is(false));
    }

    @Test
    public void twin() {
        int dimension = 3;
        int[][] blocks = new int[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                blocks[j][i] = i + (j * dimension);
            }
        }

        Board board = new Board(blocks);
        System.out.println(board);

        Board twin = board.twin();
        System.out.println(twin);
    }

    @Test
    public void neighbors() {
        int dimension = 3;
        int[][] blocks = new int[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                blocks[j][i] = i + (j * dimension);
            }
        }

        blocks[0][0] = blocks[1][1];
        blocks[1][1] = 0;

        Board board = new Board(blocks);

        Iterator<Board> neighbors = board.neighbors().iterator();

        // 4 neighbors
        neighbors.next();
        neighbors.next();
        neighbors.next();
        neighbors.next();

        assertThat(neighbors.hasNext(), is(false));
    }

}
