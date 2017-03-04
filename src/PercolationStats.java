import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private final int gridSize;
    private final int trials;
    private final double[] results;

    public PercolationStats(int gridSize, int trials) {
        if (gridSize <= 0) {
            throw new IllegalArgumentException("PercolationStats, n must be >= 0");
        }
        if (trials <= 0) {
            throw new IllegalArgumentException("PercolationStats, trials must be >= 0");
        }

        this.gridSize = gridSize;
        this.trials = trials;

        this.results = new double[trials];

        compute();
    }

    public static void main(String[] args) {
        int gridSize = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);

        PercolationStats percolationStats = new PercolationStats(gridSize, trials);
        percolationStats.compute();

        System.out.println("mean = " + percolationStats.mean());
        System.out.println("stddev = " + percolationStats.stddev());
        System.out.println("95% confidence interval = " +
                "[" + percolationStats.confidenceLo() + ", [" + percolationStats.confidenceHi() + "]");
    }

    private double proba() {
        int[] sites = StdRandom.permutation(gridSize * gridSize);
        Percolation percolation = new Percolation(gridSize);

        int i = 0;
        while (true) {
            percolation.open(getRow(sites[i]) + 1, getCol(sites[i]) + 1);
            if (percolation.percolates()) {
                return ((double) percolation.numberOfOpenSites()) / ((double) (this.gridSize * this.gridSize));
            }
            i++;
        }
    }

    private void compute() {
        for (int i = 0; i < trials; i++) {
            results[i] = proba();
        }
    }

    private int getRow(int site) {
        return site / this.gridSize;
    }

    private int getCol(int site) {
        return site % gridSize;
    }

    public double mean() {
        return StdStats.mean(results);
    }

    public double stddev() {
        return StdStats.stddev(results);
    }

    public double confidenceHi() {
        return mean() + ((2 * stddev()) / (Math.sqrt(trials)));
    }

    public double confidenceLo() {
        return mean() - ((2 * stddev()) / (Math.sqrt(trials)));
    }
}

