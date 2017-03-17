import java.util.Arrays;

public class BruteCollinearPoints {

    private final Point[] points;

    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new NullPointerException("Argument points cannot be null");
        }

        Point[] defensivePoints = Arrays.copyOf(points, points.length);

        validate(defensivePoints);

        this.points = defensivePoints;

   }

    private void validate(Point[] points) {
        Point[] defensivePoints = Arrays.copyOf(points, points.length);
        for (int i = 0; i < defensivePoints.length; i++) {
            if (defensivePoints[i] == null) {
                throw new NullPointerException("Argument points cannot contain null element");
            }
            else {
                for(int j = i + 1; j < defensivePoints.length; j++) {
                    if (defensivePoints[j] == null) {
                        throw new NullPointerException("Argument points cannot contain null element");
                    }
                    else {
                        if (defensivePoints[i].compareTo(defensivePoints[j]) == 0) {
                            throw new IllegalArgumentException("points contains a repeated point : " + defensivePoints[i]);
                        }
                    }
                }
            }
        }
    }

    public LineSegment[] segments() {
        LineSegment[] lineSegments = new LineSegment[numberOfSegments()];
        int segments = 0;
        Point[] sortedPoints = points.clone();
        Arrays.sort(sortedPoints);
        for (int i = 0; i < sortedPoints.length; i++) {
            for (int j = i + 1; j < sortedPoints.length; j++) {
                for (int k = j + 1; k < sortedPoints.length; k++) {
                    for (int l = k + 1; l < sortedPoints.length; l++) {
                        if (sortedPoints[i].slopeTo(sortedPoints[j])  == sortedPoints[i].slopeTo(sortedPoints[k])  && sortedPoints[i].slopeTo(sortedPoints[j]) == sortedPoints[i].slopeTo(sortedPoints[l])) {
                            lineSegments[segments] = new LineSegment(sortedPoints[i], sortedPoints[l]);
                            segments++;
                        }
                    }
                }
            }
        }
        return lineSegments;
    }

    public int numberOfSegments() {
        int numberOfSegments = 0;
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                for (int k = j + 1; k < points.length; k++) {
                    for (int l = k + 1; l < points.length; l++) {
                        if (points[i].slopeTo(points[j])  == points[i].slopeTo(points[k])  && points[i].slopeTo(points[j]) == points[i].slopeTo(points[l])) {
                            numberOfSegments++;
                        }
                    }
                }
            }
        }
        return numberOfSegments;
    }

}
