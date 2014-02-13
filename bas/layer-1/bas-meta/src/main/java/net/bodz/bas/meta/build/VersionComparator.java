package net.bodz.bas.meta.build;

import java.util.Comparator;

public class VersionComparator
        implements Comparator<IVersion> {

    @Override
    public int compare(IVersion o1, IVersion o2) {
        if (o1 == o2)
            return 0;
        if (o1 == null)
            return -1;
        if (o2 == null)
            return 1;
        return compareNonNull(o1, o2);
    }

    public int compareNonNull(IVersion o1, IVersion o2) {
        int minLen = Math.min(o1.size(), o2.size());
        for (int index = 0; index < minLen; index++) {
            if (o1.isInt(index) && o2.isInt(index)) {
                int i1 = o1.getInt(index);
                int i2 = o2.getInt(index);
                int cmp = i1 - i2;
                if (cmp != 0)
                    return cmp;
            } else {
                String s1 = o1.get(index);
                String s2 = o2.get(index);
                int cmp = s1.compareTo(s2);
                if (cmp != 0)
                    return cmp;
            }
        }

        if (o1.size() > minLen)
            return 1;
        if (o2.size() > minLen)
            return -1;

        String q1 = o1.getQualifier();
        String q2 = o2.getQualifier();
        if (q1 != q2) {
            if (q1 == null)
                return -1;
            if (q2 == null)
                return 1;
            int cmp = q1.compareTo(q2);
            if (cmp != 0)
                return cmp;
        }

        return 0;
    }

    public static final VersionComparator instance = new VersionComparator();

    public static VersionComparator getInstance() {
        return instance;
    }

}
