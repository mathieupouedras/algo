import java.util.Arrays;

public class FastCollinearPoints {

    private final Point[] points;
    private int numberOfSegments = -1;

    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw new NullPointerException("Argument points cannot be null");
        }

        validate(points);

        this.points = points;
    }

    private void validate(Point[] points) {
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) {
                throw new NullPointerException("Argument points cannot contain null element");
            }
            else {
                for(int j = i + 1; j < points.length; j++) {
                    if (points[j] == null) {
                        throw new NullPointerException("Argument points cannot contain null element");
                    }
                    else {
                        if (points[i].compareTo(points[j]) == 0) {
                            throw new IllegalArgumentException("points contains a repeated point : " + points[i]);
                        }
                    }
                }
            }
        }
    }

    public int numberOfSegments() {
        int first = 0;
        int last = 0;
        int count = 0;
        Point[] origins = points.clone();
        for (Point origin : origins) {
            Arrays.sort(points, origin.slopeOrder());
            for (int i = 0; i < points.length - 1; i++) {
                if (origin.slopeTo(points[i]) == origin.slopeTo(points[i + 1])) {
                    last = i + 1;
                    if (i ==  points.length - 2) {
                        if ((last - first) >= 2) {
                            Arrays.sort(points, first, last + 1);
                            if (origin.compareTo(points[first]) < 0) {
                                count++;
                            }
                        }
                    }
                } else {
                    if ((last - first) >= 2) {
                        Arrays.sort(points, first, last + 1);
                        if (origin.compareTo(points[first]) < 0) {
                            count++;
                        }
                    }
                    first = i + 1;
                }
            }
            first = 0;
            last = 0;
        }
        return count;
    }


    public LineSegment[] segments() {
        LineSegment[] lineSegments = new LineSegment[numberOfSegments()];
        int first = 0;
        int last = 0;
        int count = 0;
        Point[] origins = points.clone();
        for (Point origin : origins) {
            Arrays.sort(points, origin.slopeOrder());
            for (int i = 0; i < points.length - 1; i++) {
                if (origin.slopeTo(points[i]) == origin.slopeTo(points[i + 1])) {
                    last = i + 1;
                    if (i ==  points.length - 2) {
                        if ((last - first) >= 2) {
                            Arrays.sort(points, first, last + 1);
                            if (origin.compareTo(points[first]) < 0) {
                                lineSegments[count] = new LineSegment(origin, points[last]);
                                count++;
                            }
                        }
                    }
                } else {
                    if ((last - first) >= 2) {
                        Arrays.sort(points, first, last + 1);
                        if (origin.compareTo(points[first]) < 0) {
                            lineSegments[count] = new LineSegment(origin, points[last]);
                            count++;
                        }
                    }
                    first = i + 1;
                }
            }
            first = 0;
            last = 0;
        }
        return lineSegments;
    }
}
