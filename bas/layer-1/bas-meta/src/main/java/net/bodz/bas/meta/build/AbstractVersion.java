package net.bodz.bas.meta.build;

public abstract class AbstractVersion
        implements IVersion {

    private static final long serialVersionUID = 1L;

    public final int getMajor() {
        if (size() < 1)
            throw new IllegalVersionException("No major number.");
        if (!isInt(0))
            throw new IllegalVersionException("Major version isn't an integer.");
        return getInt(0);
    }

    public final int getMinor() {
        if (size() < 2)
            throw new IllegalVersionException("No minor number.");
        if (!isInt(1))
            throw new IllegalVersionException("Minor version isn't an integer.");
        return getInt(1);
    }

    @Override
    public int compareTo(IVersion o) {
        VersionComparator comparator = VersionComparator.getInstance();
        return comparator.compare(this, o);
    }

    @Override
    public String toString() {
        int n = size();
        StringBuilder buf = new StringBuilder(n * 10);
        for (int i = 0; i < n; i++) {
            if (i != 0)
                buf.append('.');
            buf.append(get(i));
        }
        String qualifier = getQualifier();
        if (qualifier != null) {
            buf.append('-');
            buf.append(qualifier);
        }
        return buf.toString();
    }

}
