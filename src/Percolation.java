import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {

    private final int gridSize;
    private final WeightedQuickUnionUF unionFind;
    private final boolean[][] grid;
    private int openSitesCount = 0;
    private boolean isPercolating = false;

    public Percolation(int gridSize) {
        if (gridSize <= 0) {
            throw new IllegalArgumentException(
                    "gridSize should be greater than 0 (" + String.valueOf(gridSize) + ")");
        }
        this.gridSize = gridSize;
        unionFind = new WeightedQuickUnionUF(this.gridSize * this.gridSize);

        grid = new boolean[this.gridSize][this.gridSize];
        for (int i  = 0; i < this.gridSize; i++) {
            for (int j = 0; j < this.gridSize; j++) {
                grid[i][j] = false;
            }
        }
    }

    public void open(int row, int col) {
        if (this.isOutsideRange(row)) {
            throw new IllegalArgumentException(buildErrorMessage(row));
        }

        if (this.isOutsideRange(col)) {
            throw new IllegalArgumentException(buildErrorMessage(col));
        }

        this.grid[row - 1][col - 1] = true;
        openSitesCount++;
        unionWithNeighbor(row, col);
        if (this.isFull(row, col)) {
            this.isPercolating = true;
        }

    }

    private void unionWithNeighbor(int row, int col) {
        // North
        if (row > 1) {
            if (this.isOpen(row - 1, col)) {
                this.unionFind.union(getSiteNumber(row, col), getSiteNumber(row - 1, col));
            }
        }
        // south
        if (row < this.gridSize) {
            if (this.isOpen(row + 1, col)) {
                this.unionFind.union(getSiteNumber(row, col), getSiteNumber(row + 1, col));
            }
        }
        // east
        if (col > 1) {
            if (this.isOpen(row , col - 1)) {
                this.unionFind.union(getSiteNumber(row, col), getSiteNumber(row , col - 1));
            }
        }
        // west
        if (col < this.gridSize) {
            if (this.isOpen(row , col + 1)) {
                this.unionFind.union(getSiteNumber(row, col), getSiteNumber(row , col + 1));
            }
        }
    }

    public boolean isOpen(int row, int col) {
        if (this.isOutsideRange(row)) {
            throw new IllegalArgumentException(buildErrorMessage(row));
        }

        if (this.isOutsideRange(col)) {
            throw new IllegalArgumentException(buildErrorMessage(col));
        }

        return this.grid[row -1][col-1];
    }

    public boolean isFull(int row, int col) {
        if (this.isOutsideRange(row)) {
            throw new IllegalArgumentException(buildErrorMessage(row));
        }

        if (this.isOutsideRange(col)) {
            throw new IllegalArgumentException(buildErrorMessage(col));
        }
        for (int topSite = 0; topSite < this.gridSize - 1; topSite++) {
            int component = unionFind.find(getSiteNumber(row, col));
            if ( component == unionFind.find(getSiteNumber(1, topSite +1))) {
                if (component == unionFind.find(getSiteNumber(this.gridSize, topSite +1))) {
                    return true;
                }
            }
        }

        return false;
    }

    public int numberOfOpenSites() {
        return this.openSitesCount;
    }

    public boolean percolates() {
        return this.isPercolating;
    }

    private int getSiteNumber(int row, int col) {
        return ((row - 1) * this.gridSize) + col;
    }

    private boolean isOutsideRange(int value) {
        if (value <= 0) return true;
        if (value > this.gridSize ) return true;
        return false;
    }

    private String buildErrorMessage(int value) {
        final String incorrectValue = String.valueOf(value);
        final String acceptedRange = "[1-" + String.valueOf(gridSize) + "]";

        return incorrectValue + " is outside accepted range " + acceptedRange;
    }

    public static void main(String[] args) {
        Percolation percolation = new Percolation(4);
        percolation.open(1, 1);
        percolation.open(2, 1);
        percolation.open(3, 1);
        percolation.open(4, 1);

        System.out.println(percolation.percolates());

    }

}
