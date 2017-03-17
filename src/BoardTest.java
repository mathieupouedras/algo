import org.junit.Test;

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
                blocks[i][j] = i + j;
            }
        }

        Board board = new Board(blocks);

        StringBuilder expectedString = new StringBuilder();
        expectedString.append(dimension);
        expectedString.append("\n");
        expectedString.append(" 0  1 ");
        expectedString.append("\n");
        expectedString.append(" 1  2 ");
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

    @Test public void shoud_manathan_3() {
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

}
