import java.util.Arrays;

public class BruteCollinearPoints
{
    private int number = 0;
    private LineSegment[] lineSegments;

    public BruteCollinearPoints(Point[] points)    // finds all line segments containing 4 points
    {
        checkException(points);
        for (int p = 0; p < points.length - 3; p++)
        {
            for (int q = p + 1; q < points.length - 2; q++)
            {
                for (int r = q + 1; r < points.length - 1; r++)
                {
                    for (int s = r + 1; r < points.length; s++)
                    {
                        if (points[p].slopeTo(points[q]) == points[p].slopeTo(points[r]) && points[p].slopeTo(points[q]) == points[p].slopeTo(points[s]))
                        {
                            Point[] sortedPoints = segPointsOrder(points[p], points[q], points[r], points[s]);
                            lineSegments[number++] = new LineSegment(sortedPoints[0], sortedPoints[3]);
                        }
                    }
                }
            }
        }
    }

    private Point[] segPointsOrder(Point p1, Point p2, Point p3, Point p4)
    {
        Point[] segPoints = new Point[4];
        segPoints[0] = p1;
        segPoints[1] = p2;
        segPoints[2] = p3;
        segPoints[3] = p4;
        Arrays.sort(segPoints);
        return segPoints;
    }

    private void checkException(Point[] pointsArray)
    {
        if (pointsArray == null)
            throw new java.lang.NullPointerException();
        for (int i = 0; i < pointsArray.length - 1; i++)
        {
            for (int j = i + 1; j < pointsArray.length; j++)
            {
                if (pointsArray[i] == null)
                    throw new java.lang.NullPointerException();
                if (pointsArray[i].compareTo(pointsArray[j])==0)
                    throw new java.lang.IllegalArgumentException();
            }
        }
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
