package net.bodz.bas.meta.build;

import java.io.Serializable;

public class VersionRange
        implements Comparable<VersionRange>, Serializable {

    private static final long serialVersionUID = 1L;

    private IVersion lowerBound;
    private IVersion upperBound;

    public VersionRange() {
    }

    public VersionRange(IVersion lowerBound) {
        this.lowerBound = lowerBound;
    }

    public VersionRange(IVersion lowerBound, IVersion upperBound) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    /**
     * Inclusive.
     */
    public IVersion getLowerBound() {
        return lowerBound;
    }

    public void setLowerBound(IVersion lowerBound) {
        this.lowerBound = lowerBound;
    }

    /**
     * Exclusive.
     */
    public IVersion getUpperBound() {
        return upperBound;
    }

    public void setUpperBound(IVersion upperBound) {
        this.upperBound = upperBound;
    }

    public boolean contains(IVersion version) {
        if (version == null)
            throw new NullPointerException("version");

        if (lowerBound != null)
            if (version.compareTo(lowerBound) < 0)
                return false;

        if (upperBound != null)
            if (version.compareTo(upperBound) >= 0)
                return false;

        return true;
    }

    public void intersect(VersionRange range) {
        lowerBound = IVersion.fn.maxClosed(lowerBound, range.lowerBound);
        upperBound = IVersion.fn.minClosed(upperBound, range.upperBound);
    }

    public void union(VersionRange range) {
        lowerBound = IVersion.fn.minOpen(lowerBound, range.lowerBound);
        upperBound = IVersion.fn.maxOpen(upperBound, range.upperBound);
    }

    @Override
    public int compareTo(VersionRange o) {
        if (o == null)
            return 1;

        if (lowerBound != o.lowerBound) {
            if (lowerBound == null)
                return -1;
            int cmp = lowerBound.compareTo(o.lowerBound);
            if (cmp != 0)
                return cmp;
        }

        if (upperBound != o.upperBound) {
            if (upperBound == null)
                return -1;
            int cmp = upperBound.compareTo(o.upperBound);
            if (cmp != 0)
                return cmp;
        }

        return 0;
    }

    /**
     * [1.1.0,2.2-dev)
     */
    @Override
    public String toString() {
        return "[" + lowerBound + "," + upperBound + ")";
    }

}
