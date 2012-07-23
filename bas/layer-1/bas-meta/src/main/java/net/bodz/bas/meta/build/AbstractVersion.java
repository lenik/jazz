package net.bodz.bas.meta.build;

import java.util.Arrays;

import net.bodz.bas.err.IllegalUsageException;

public abstract class AbstractVersion
        implements IVersion {

    private static final long serialVersionUID = 1L;

    public final int getMajorVersion() {
        String[] elements = getVersionElements();
        if (elements.length < 2)
            throw new IllegalUsageException("Bad version elements: must be major.minor.*");
        String s = elements[0];
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw new IllegalUsageException("Major version should be integer.");
        }
    }

    public final int getMinorVersion() {
        String[] elements = getVersionElements();
        if (elements.length < 2)
            throw new IllegalUsageException("Bad version elements: must be major.minor.*");
        String s = elements[1];
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw new IllegalUsageException("Minor version should be integer.");
        }
    }

    @Override
    public int compareTo(IVersion o) {
        VersionComparator comparator = VersionComparator.getInstance();
        return comparator.compare(this, o);
    }

    @Override
    public int hashCode() {
        String[] elements = getVersionElements();
        int hash = Arrays.hashCode(elements) * 17;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof AbstractVersion))
            return false;
        AbstractVersion v = (AbstractVersion) obj;
        String[] v1 = getVersionElements();
        String[] v2 = v.getVersionElements();
        return Arrays.equals(v1, v2);
    }

    @Override
    public String toString() {
        String[] elements = getVersionElements();
        StringBuilder buf = new StringBuilder(elements.length * 10);
        for (int i = 0; i < elements.length; i++) {
            if (i != 0)
                buf.append('.');
            buf.append(elements[i]);
        }
        return buf.toString();
    }

}
