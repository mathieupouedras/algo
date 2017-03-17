import edu.princeton.cs.algs4.In;
import org.junit.Test;

public class BruteCollinearPointsTest {

    @Test(expected = NullPointerException.class)
    public void should_throw_npe_when_points_argument_is_null() {
        BruteCollinearPoints bruteCollinearPoints = new BruteCollinearPoints(null);
    }

    @Test(expected = NullPointerException.class)
    public void should_throw_npe_when_points_contains_null_element() {
        Point[] points = new Point[2];
        points[0] = new Point(1, 1);

        BruteCollinearPoints bruteCollinearPoints = new BruteCollinearPoints(points);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_IllegalArgumentException() {
        Point[] points = new Point[3];
        points[0] = new Point(1, 2);
        points[1] = new Point(2, 2);
        points[2] = new Point(1, 2);

        BruteCollinearPoints bruteCollinearPoints = new BruteCollinearPoints(points);

    }

    @Test
    public void test() {
        String testFile = "C:\\dev\\algo\\test_data_sort\\input8.txt";
        In in = new In(testFile);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        BruteCollinearPoints bruteCollinearPoints = new BruteCollinearPoints(points);
        System.out.println(bruteCollinearPoints.numberOfSegments());
        for (LineSegment lineSegment : bruteCollinearPoints.segments()) {
            System.out.println(lineSegment);
        }

        points = null;
        System.out.println(bruteCollinearPoints.numberOfSegments());
    }

}
