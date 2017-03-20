import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SolverTest {

    @Test
    public void is_not_solvable() {
        int dimension = 3;
        int[][] blocks = new int[dimension][dimension];

        blocks[0][0] = 1;
        blocks[0][1] = 2;
        blocks[0][2] = 3;
        blocks[1][0] = 4;
        blocks[1][1] = 5;
        blocks[1][2] = 6;
        blocks[2][0] = 8;
        blocks[2][1] = 7;
        blocks[2][2] = 0;

        Board board = new Board(blocks);

        Solver solver = new Solver(board);

        assertThat(solver.isSolvable(), is(false));

    }

}