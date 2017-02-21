import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;


public class PercolationStats
{
    private double[] resultsArray;

    public PercolationStats(int n, int trials)// perform trials independent experiments on an n-by-n grid
    {
        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException("Wrong Arguments");

        resultsArray = new double[trials];
        for (int i = 0; i < trials; i++)
        {
            Percolation perco = new Percolation(n);
            while (!perco.percolates())
            {
                perco.open(StdRandom.uniform(1, n + 1), StdRandom.uniform(1, n + 1));
            }
            resultsArray[i] = (double) perco.numberOfOpenSites() / (n * n);
        }

    }

    public double mean()                          // sample mean of percolation threshold
    {
        return StdStats.mean(resultsArray);
    }

    public double stddev()                        // sample standard deviation of percolation threshold
    {
        return StdStats.stddev(resultsArray);
    }

    public double confidenceLo()                  // low  endpoint of 95% confidence interval
    {
        double min = resultsArray[0];
        for (int i = 0; i < resultsArray.length; i++)
        {
            if (resultsArray[i] < min)
                min = resultsArray[i];
        }
        return min;
    }

    public double confidenceHi()                  // high endpoint of 95% confidence interval
    {
        double max = resultsArray[0];
        for (int i = 0; i < resultsArray.length; i++)
        {
            if (resultsArray[i] > max)
                max = resultsArray[i];
        }
        return max;
    }

    public static void main(String[] args)        // test client (described below)
    {
        PercolationStats pStats = new PercolationStats(200, 100);
        StdOut.println("mean = " + pStats.mean());
        StdOut.println("stddev = " + pStats.stddev());
        StdOut.println("95% confidence interval = [" + pStats.confidenceLo() + ", " + pStats.confidenceHi() + "]");

    }



}
