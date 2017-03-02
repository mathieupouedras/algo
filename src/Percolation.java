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
        for (int i = 0; i < this.gridSize; i++) {
            for (int j = 0; j < this.gridSize; j++) {
                grid[i][j] = false;
            }
        }
    }

    public static void main(String[] args) {
        Percolation percolation = new Percolation(4);

        percolation.open(2, 3);
        percolation.open(3, 3);
        percolation.open(3, 2);
        percolation.open(4, 2);
        percolation.open(1, 3);

        System.out.println(percolation.isFull(1, 3));

        System.out.println(percolation.printGrid());


        System.out.println(percolation.percolates());

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
            if (this.isOpen(row, col - 1)) {
                this.unionFind.union(getSiteNumber(row, col), getSiteNumber(row, col - 1));
            }
        }
        // west
        if (col < this.gridSize) {
            if (this.isOpen(row, col + 1)) {
                this.unionFind.union(getSiteNumber(row, col), getSiteNumber(row, col + 1));
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

        return this.grid[row - 1][col - 1];
    }

    public boolean isFull(int row, int col) {
        if (this.isOutsideRange(row)) {
            throw new IllegalArgumentException(buildErrorMessage(row));
        }

        if (this.isOutsideRange(col)) {
            throw new IllegalArgumentException(buildErrorMessage(col));
        }
        int component = unionFind.find(getSiteNumber(row, col));
        for (int topSite = 0; topSite < this.gridSize - 1; topSite++) {
            int topComponent = unionFind.find(getSiteNumber(1, topSite + 1));
            if (component == topComponent) {
                for (int botomSite = 0; botomSite < this.gridSize - 1; botomSite++) {
                    int bottomComponent = unionFind.find(getSiteNumber(this.gridSize, botomSite + 1));
                    if (component == bottomComponent) {
                        return true;
                    }
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
        return ((row - 1) * this.gridSize) + col - 1;
    }

    private boolean isOutsideRange(int value) {
        if (value <= 0) return true;
        if (value > this.gridSize) return true;
        return false;
    }

    private String buildErrorMessage(int value) {
        final String incorrectValue = String.valueOf(value);
        final String acceptedRange = "[1-" + String.valueOf(gridSize) + "]";

        return incorrectValue + " is outside accepted range " + acceptedRange;
    }

    private String printGrid() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int row = 0; row < this.gridSize; row++) {
            for (int col = 0; col < this.gridSize; col++) {
                stringBuilder.append(grid[row][col] ? 1 + " " : 0 + " ");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

}
