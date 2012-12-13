package net.bodz.bas.meta.build;

import net.bodz.bas.t.order.AbstractNonNullComparator;

public class VersionComparator
        extends AbstractNonNullComparator<IVersion> {

    @Override
    public int compareNonNull(IVersion o1, IVersion o2) {
        String[] v1 = o1.getVersionElements();
        String[] v2 = o2.getVersionElements();

        int minLen = Math.min(v1.length, v2.length);
        for (int i = 0; i < minLen; i++) {
            String e1 = v1[i];
            String e2 = v2[i];
            int cmp = e1.compareTo(e2);
            if (cmp != 0)
                return cmp;
        }

        if (v1.length > minLen)
            return 1;
        else if (v2.length > minLen)
            return -1;
        else
            return 0;
    }

    public static final VersionComparator instance = new VersionComparator();

    public static VersionComparator getInstance() {
        return instance;
    }

}
