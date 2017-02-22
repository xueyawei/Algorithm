import java.util.Arrays;

public class FastCollinearPoints
{
    int number = 0;
    LineSegment[] lineSegments;

    public FastCollinearPoints(Point[] points)     // finds all line segments containing 4 or more points
    {
        Arrays.sort(points); // TODO: use comparator

    }

    public int numberOfSegments()        // the number of line segments
    {
        return number;
    }

    public LineSegment[] segments()                // the line segments
    {
        return lineSegments;
    }
}
