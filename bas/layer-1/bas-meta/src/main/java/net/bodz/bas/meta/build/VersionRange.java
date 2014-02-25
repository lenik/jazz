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

    public static VersionRange parse(String minVersionStr) {
        IVersion minVersion = null;
        if (minVersionStr != null)
            minVersion = IVersion.fn.parse(minVersionStr);
        return new VersionRange(minVersion);
    }

    public static VersionRange parse(String minVersionStr, String maxVersionStr) {
        IVersion minVersion = null;
        IVersion maxVersion = null;
        if (minVersionStr != null)
            minVersion = IVersion.fn.parse(minVersionStr);
        if (maxVersionStr != null)
            maxVersion = IVersion.fn.parse(maxVersionStr);
        return new VersionRange(minVersion, maxVersion);
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
     * Inclusive.
     */
    public IVersion getUpperBound() {
        return upperBound;
    }

    public void setUpperBound(IVersion upperBound) {
        this.upperBound = upperBound;
    }

    public boolean isEmpty() {
        if (lowerBound == null || upperBound == null)
            return false;
        return lowerBound.compareTo(upperBound) > 0;
    }

    public boolean contains(IVersion needle) {
        if (needle == null)
            throw new NullPointerException("needle");

        if (lowerBound != null)
            if (needle.compareTo(lowerBound) < 0)
                return false;

        if (upperBound != null)
            if (needle.compareTo(upperBound) > 0)
                return false;

        return true;
    }

    public boolean intersects(VersionRange range) {
        return !intersect(range).isEmpty();
    }

    public VersionRange intersect(VersionRange range) {
        IVersion lb = IVersion.fn.maxClosed(lowerBound, range.lowerBound);
        IVersion ub = IVersion.fn.minClosed(upperBound, range.upperBound);
        return new VersionRange(lb, ub);
    }

    public VersionRange union(VersionRange range) {
        IVersion lb = IVersion.fn.minOpen(lowerBound, range.lowerBound);
        IVersion ub = IVersion.fn.maxOpen(upperBound, range.upperBound);
        return new VersionRange(lb, ub);
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
