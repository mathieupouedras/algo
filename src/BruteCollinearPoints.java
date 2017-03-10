import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

import java.util.Arrays;

public class BruteCollinearPoints {

    private final Point[] points;

    public BruteCollinearPoints(Point[] points) {
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
        int numberOfSegments = 0;
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                for (int k = j + 1; k < points.length; k++) {
                    for (int l = k + 1; l < points.length; l++) {
                        if (points[i].compareTo(points[j]) == 0 && points[i].compareTo(points[k]) == 0 && points[i].compareTo(points[l]) == 0) {
                            numberOfSegments++;
                        }
                    }
                }
            }
        }
        return numberOfSegments;
    }

}
