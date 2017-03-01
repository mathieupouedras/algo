import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

import java.util.Arrays;

public class PercolationStats {

    private Percolation percolation;
    private final int gridSize;

    public PercolationStats(int gridSize, int trials) {
        if (gridSize <= 0) {
            throw new IllegalArgumentException("PercolationStats, n must be >= 0");
        }
        if (trials <= 0) {
            throw new IllegalArgumentException("PercolationStats, trials must be >= 0");
        }

        this.gridSize = gridSize;

        for (int i = 0; i < trials; i++) {
            percolation = new Percolation(gridSize);
            System.out.println(proba());
        }
    }

    public double proba() {
        int[] sites = StdRandom.permutation(gridSize * gridSize);

        int i = 0;
        while(true) {
            percolation.open(getRow(i) + 1, getCol(i) + 1);
            if (percolation.percolates()) {
                return percolation.numberOfOpenSites() / (this.gridSize * this.gridSize);
            }
            i++;
        }
    }

    private int getRow(int site) {
        return site / this.gridSize;
    }

    private int getCol(int site) {
        return site % gridSize;
    }


    public static void main(String[] args) {
        int gridSize = Integer.valueOf(args[0]);
        int trials = Integer.valueOf(args[1]);

        PercolationStats percolationStats = new PercolationStats(gridSize, trials);

    }

}


