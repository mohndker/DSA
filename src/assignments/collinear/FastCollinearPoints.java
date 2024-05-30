package assignments.collinear;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FastCollinearPoints {
    private final List<LineSegments> segments = new ArrayList<>();
    private int numberOfSegments;

    public FastCollinearPoints(Point[] points) {
        if (points == null || isAnyPointNull(points)) {
            throw new IllegalArgumentException();
        }

        Point[] clonePoints = new Point[points.length];
        System.arraycopy(points, 0, clonePoints, 0, points.length);
        Arrays.sort(clonePoints);

        if (isDuplicatePointsFound(clonePoints)) throw new IllegalArgumentException();

        int n = points.length;
        for (int i = 0; i < n; i++) {
            Point p = clonePoints[i];
            Point[] slopeOrderedPoints = new Point[points.length];
            System.arraycopy(clonePoints, 0, slopeOrderedPoints, 0, points.length);

            Arrays.sort(slopeOrderedPoints, p.slopeOrder());

            int j = 1;
            while (j < n) {
                List<Point> candidates = new ArrayList<>();
                final double slope = p.slopeTo(slopeOrderedPoints[j]);
                do {
                    candidates.add(slopeOrderedPoints[j++]);
                } while (j < n && p.slopeTo(slopeOrderedPoints[j]) == slope);

                if (candidates.size() >= 3) {
                    candidates.add(p);
                    Point[] collinearPoints = candidates.toArray(new Point[0]);
                    Arrays.sort(collinearPoints);
                    if (collinearPoints[0] == p) {
                        segments.add(
                                new LineSegments(p, collinearPoints[collinearPoints.length - 1]));
                        numberOfSegments++;
                    }
                }
            }
        }
    }

    public int numberOfSegments() {
        return numberOfSegments;
    }

    public LineSegments[] segments() {
        return segments.toArray(new LineSegments[0]);
    }

    private boolean isAnyPointNull(Point[] points) {
        for (int i = 0; i < points.length; i++)
            if (points[i] == null) return true;
        return false;
    }

    private boolean isDuplicatePointsFound(Point[] points) {
        for (int i = 1; i < points.length; i++)
            if (points[i].compareTo(points[i - 1]) == 0) return true;
        return false;
    }

    public static void main(String[] args) {
        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegments segment : collinear.segments()) {
            System.out.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
