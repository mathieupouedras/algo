import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PointTest {

    @Test
    public void should_return_0_when_comparing_identical_points() {
        int x= 3;
        int y = 5;
        Point point1 = new Point(3, 5);
        Point point2 = new Point(3, 5);

        assertThat(point1.compareTo(point2), is(0));
    }

    @Test
    public void should_return_1_when_point1_has_greater_y() {
        Point point1 = new Point(5, 6);
        Point point2 = new Point(5, 5);

        assertThat(point1.compareTo(point2), is(1));
    }

    @Test
    public void should_return_minus1_when_point1_has_lower_y() {
        Point point1 = new Point(5, 4);
        Point point2 = new Point(5, 5);

        assertThat(point1.compareTo(point2), is(-1));
    }

    @Test
    public void should_return_1_when_y_are_equas_and_x1_is_greater() {
        Point point1 = new Point(5, 5);
        Point point2 = new Point(4, 5);
    }

    @Test
    public void should_return_minus1_when_y_are_equas_and_x1_is_lower() {
        Point point1 = new Point(5, 5);
        Point point2 = new Point(6, 5);
    }

    @Test
    public void slopeTo_should_return_positive_infinite_for_a_vertical_segment() {
        Point point1 = new Point(5, 3);
        Point point2 = new Point(5, 5);

        assertThat(point1.slopeTo(point2), is(Double.POSITIVE_INFINITY));
    }

    @Test
    public void slopeTo_should_return_negative_infinite_for_a_degenerate_segment() {
        Point point1 = new Point(5, 5);
        Point point2 = new Point(5, 5);

        assertThat(point1.slopeTo(point2), is(Double.NEGATIVE_INFINITY));
    }

    @Test
    public void slopeTo_should_return_1_and_a_half() {

        Point point1 = new Point(3, 2);
        Point point2 = new Point(5, 5);

        assertThat(point1.slopeTo(point2), is(1.5));
    }

    @Test
    public void test() {
        Point point1 = new Point(3, 5);
        Point point2 = new Point(3, 5);

        System.out.println(point1.slopeOrder().compare(point1, point2));
    }



}
