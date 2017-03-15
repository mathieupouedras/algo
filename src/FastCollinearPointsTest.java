import edu.princeton.cs.algs4.In;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class FastCollinearPointsTest {

    @Test
    public void test() {
        String testFile = "C:\\dev\\algo\\test_data_sort\\input40.txt";
        In in = new In(testFile);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        FastCollinearPoints fastCollinearPoints = new FastCollinearPoints(points);
        Arrays.stream(fastCollinearPoints.segments()).forEach(System.out::println);



    }

}