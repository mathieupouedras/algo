import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {

    private final int size;
    private final Site[] sites;
    private final WeightedQuickUnionUF weightedQuickUnionUF;
    private int openSitesCount = 0;
    private Site virtualTop;
    private Site virtualBottom;

    public Percolation(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("illegal size : " + size);
        }
        this.size = size;
        this.weightedQuickUnionUF = new WeightedQuickUnionUF(size * size + 2);

        sites = new Site[size * size + 2];

        for (int i = 0; i < sites.length; i++) {
            sites[i] = new Site(i);
        }

        virtualTop = sites[size * size];
        virtualTop.open();
        virtualBottom = sites[size * size + 1];
        virtualBottom.open();
    }

    public void open(int row, int col) {
        checkRange(row, col);

        Site site =  getSite(row, col);

        if (site.isOpen()) {
            return;
        }

        site.open();
        openSitesCount++;

        // Connect with virtual top
        if (row == 1) {
            weightedQuickUnionUF.union(site.getSiteNumber(), virtualTop.getSiteNumber());
        }

        if (row == this.size) {
            weightedQuickUnionUF.union(site.getSiteNumber(), virtualBottom.getSiteNumber());
        }

        Site siteNorth = getSite(row - 1, col);
        if (siteNorth  != null && siteNorth.isOpen()) {
            weightedQuickUnionUF.union(site.getSiteNumber(), siteNorth.getSiteNumber());
        }

        Site siteSouth = getSite(row + 1, col);
        if (siteSouth  != null && siteSouth.isOpen()) {
            weightedQuickUnionUF.union(site.getSiteNumber(), siteSouth.getSiteNumber());
        }

        Site siteWest = getSite(row, col - 1);
        if (siteWest  != null && siteWest.isOpen()) {
            weightedQuickUnionUF.union(site.getSiteNumber(), siteWest.getSiteNumber());
        }

        Site siteEast = getSite(row, col + 1);
        if (siteEast  != null && siteEast.isOpen()) {
            weightedQuickUnionUF.union(site.getSiteNumber(), siteEast.getSiteNumber());
        }
    }

    public boolean isOpen(int row, int col) {
        checkRange(row, col);

        Site site = getSite(row, col);
        return site.isOpen();
    }

    public boolean isFull(int row, int col) {
        checkRange(row, col);

        Site site = getSite(row, col);
        return weightedQuickUnionUF.connected(site.getSiteNumber(), this.virtualTop.getSiteNumber());
    }

    public int numberOfOpenSites() {
        return this.openSitesCount;
    }

    public boolean percolates() {
        return weightedQuickUnionUF.connected(this.virtualTop.getSiteNumber(), this.virtualBottom.getSiteNumber());
    }

    private boolean isRangeInvalid(int row, int col) {
        return row < 1 || row > this.size || col < 1 || col > this.size;
    }

    private void checkRange(int row, int col) {
        if (isRangeInvalid(row, col)) {
            throw new IndexOutOfBoundsException(buildErrorMessage(row, col));
        }
    }

    private String buildErrorMessage(int row, int col) {
        String range = "[" + row + "-" + col + "]";
        String prescribedRange = "[1-" + this.size + "]";
        return range + " is outside " + prescribedRange;
    }

    private Site getSite(int row, int col) {
        if (isRangeInvalid(row, col)) {
            return null;
        }
        int siteNumber = ((row - 1) * size) + col - 1;
        return this.sites[siteNumber];
    }

    private String printGrid() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int row = 1; row <= this.size; row++) {
            for (int col = 1; col <= this.size; col++) {
                Site site = getSite(row, col);
                stringBuilder.append(site.isOpen() ? "1 " : "0 ");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        Percolation percolation = new Percolation(3);
        System.out.println(percolation.percolates());
        percolation.open(1, 1);
        percolation.open(2, 1);
        percolation.open(2, 2);
        percolation.open(3, 2);
        System.out.println(percolation.percolates());
        percolation.printGrid();
    }
}

class Site {
    private boolean blocked;
    private final int siteNumber;

    public Site(int siteNumber) {
        this.siteNumber = siteNumber;
        this.blocked = true;
    }

    public boolean isOpen() {
        return !blocked;
    }

    public void open() {
        this.blocked = false;
    }

    public int getSiteNumber() {
        return this.siteNumber;
    }

    @Override
    public String toString() {
        return siteNumber + "[" + isOpen() + "]";
    }
}
