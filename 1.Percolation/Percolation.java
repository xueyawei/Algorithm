import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation
{

    private int[][] grid;
    private final int gridSize;
    private int numberOfOpenSites;
    private WeightedQuickUnionUF ufObj;


    public Percolation(int n)                // create n-by-n grid, with all sites blocked
    {
        if (n <= 0)
            throw new IllegalArgumentException("Illegal Argument");

        int actualN = n + 1;
        gridSize = n;
        grid = new int[actualN][actualN];
        for (int i = 0; i < actualN; i++)
        {
            for (int j = 0; j < actualN; j++)
            {
                grid[i][j] = 0;             // "0" is blocked
            }
        }
        numberOfOpenSites = 0;

        ufObj = new WeightedQuickUnionUF(actualN * actualN);


    }

    public void open(int row, int col)    // open site (row, col) if it is not open already
    {

        if ((row < 1 || row > gridSize) || (col < 1 || col > gridSize))
            throw new IndexOutOfBoundsException("Index out of Bounds");
        if (grid[row][col] == 0)
        {
            grid[row][col] = 1;
            numberOfOpenSites++;

            // union virtual point
            if (row == 1)
            {
                ufObj.union(xyTo1D(row, col), 0); // ufObj[0] : virtual top
            } else if (row == gridSize)
            {
                ufObj.union(xyTo1D(row, col), 1);  // ufObj[1]: virtual down
            }
            unionNeighbor(row, col);


        }
        // backwash fix



    }

    public boolean isOpen(int row, int col)  // is site (row, col) open?
    {
        if ((row < 1 || row > gridSize) || (col < 1 || col > gridSize))
            throw new IndexOutOfBoundsException("Index out of Bounds");

        return grid[row][col] == 1;

    }

    public boolean isFull(int row, int col)  // is site (row, col) full?
    {
        if ((row < 1 || row > gridSize) || (col < 1 || col > gridSize))
            throw new IndexOutOfBoundsException("Index out of Bounds");

        return ufObj.connected(xyTo1D(row, col), 0);


    }

    public int numberOfOpenSites()       // number of open sites
    {
        return numberOfOpenSites;
    }

    public boolean percolates()              // does the system percolate?
    {
        return ufObj.connected(0, 1);
    }

    public static void main(String[] args)   // test client (optional)
    {

    }




    private int xyTo1D(int row, int col)
    {

        return row * (gridSize + 1) + col;
    }

    private void unionNeighbor(int row, int col)
    {
        if (grid[row - 1][col] == 1)
        {  // upper point
            ufObj.union(xyTo1D(row, col), xyTo1D(row - 1, col));

        }
        if (grid[row][col - 1] == 1)
        {  // left point
            ufObj.union(xyTo1D(row, col), xyTo1D(row, col - 1));

        }
        if (row != gridSize && col != gridSize)
        {
            if (grid[row + 1][col] == 1)
            { // lower point != downside border
                ufObj.union(xyTo1D(row, col), xyTo1D(row + 1, col));

            }
            if (grid[row][col + 1] == 1)
            { // right point != right border
                ufObj.union(xyTo1D(row, col), xyTo1D(row, col + 1));

            }
        } else if (row != gridSize && col == gridSize)
        {
            // union lower
            if (grid[row + 1][col] == 1)
            {
                ufObj.union(xyTo1D(row, col), xyTo1D(row + 1, col));

            }
        } else if (col != gridSize && row == gridSize)
        {
            // union right
            if (grid[row][col + 1] == 1)
            {
                ufObj.union(xyTo1D(row, col), xyTo1D(row, col + 1));

            }
        }


    }
}
