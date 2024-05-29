package assignments.collinear;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {

    private final List<LineSegments> segmentList = new ArrayList<>();

    public BruteCollinearPoints(Point[] points) {
        if (points == null || isAnyPointNull(points) || isAnyPointRepeated(points))
            throw new IllegalArgumentException();

        int n = points.length;
        Point extermalPoint = new Point(0, 0);

        for (int p = 0; p < n - 3; p++) {
            for (int q = p + 1; q < n - 2; q++) {
                for (int r = q + 1; r < n - 1; r++) {
                    for (int s = r + 1; s < n; s++) {
                        Point p1 = points[p];
                        Point p2 = points[q];
                        Point p3 = points[r];
                        Point p4 = points[s];

                        if (p1.slopeTo(p2) == p1.slopeTo(p3) && p1.slopeTo(p2) == p1.slopeTo(p4)
                                && p1.slopeTo(extermalPoint) != p1.slopeTo(p4)) {
                            segmentList.add(new LineSegments(p1, p4));
                            extermalPoint = p4;
                        }
                    }
                }
            }
        }
    }

    public int numberOfSegments() {
        return segmentList.size();
    }

    public LineSegments[] segments() {
        return segmentList.toArray(new LineSegments[0]);
    }

    private boolean isAnyPointNull(Point[] points) {
        for (int i = 0; i < points.length; i++)
            if (points[i] == null) return true;
        return false;
    }

    private boolean isAnyPointRepeated(Point[] points) {
        Arrays.sort(points);
        for (int i = 0; i < points.length - 1; i++)
            if (points[i].compareTo(points[i + 1]) == 0)
                return true;
        return false;
    }
}
