package net.bodz.bas.t.catalog;

import net.bodz.bas.t.order.AbstractNonNullComparator;

public class CrossReferenceComparator
        extends AbstractNonNullComparator<CrossReference> {

    int sign;
    TableKeyComparator tableKeyCmp;

    public CrossReferenceComparator(int sign, TableKeyComparator tableKeyCmp) {
        this.sign = sign;
        this.tableKeyCmp = tableKeyCmp;
    }

    @Override
    public int compareNonNull(CrossReference o1, CrossReference o2) {
        int cmp;
        cmp = compareString(o1.constraintName, o2.constraintName);
        if (cmp != 0)
            return cmp * sign;

        cmp = compareString(o1.primaryKeyName, o2.primaryKeyName);
        if (cmp != 0)
            return cmp * sign;

        cmp = tableKeyCmp.compare(o1.foreignKey, o2.foreignKey);
        if (cmp != 0)
            return cmp * sign;

        cmp = tableKeyCmp.compare(o1.parentKey, o2.parentKey);
        if (cmp != 0)
            return cmp * sign;

        cmp = o1.updateRule - o2.updateRule;
        if (cmp != 0)
            return cmp * sign;

        cmp = o1.deleteRule - o2.deleteRule;
        if (cmp != 0)
            return cmp * sign;

        cmp = o1.deferrability - o2.deferrability;
        return cmp * sign;
    }

    private final int compareString(String a, String b) {
        if (a == b)
            return 0;
        if (a == null)
            return -1;
        if (b == null)
            return 1;
        return compareNonNullString(a, b);
    }

    protected int compareNonNullString(String a, String b) {
        return a.compareTo(b);
    }

    public static final CrossReferenceComparator ASC = new CrossReferenceComparator(1, TableKeyComparator.ASC);
    public static final CrossReferenceComparator DESC = new CrossReferenceComparator(-1, TableKeyComparator.DESC);

    public static class CaseInsensitive
            extends CrossReferenceComparator {

        public CaseInsensitive(int sign, TableKeyComparator tableKeyCmp) {
            super(sign, tableKeyCmp);
        }

        @Override
        protected int compareNonNullString(String a, String b) {
            return a.compareToIgnoreCase(b);
        }

        public static final CrossReferenceComparator ASC = new CaseInsensitive(1,
                TableKeyComparator.CaseInsensitive.ASC);
        public static final CrossReferenceComparator DESC = new CaseInsensitive(-1,
                TableKeyComparator.CaseInsensitive.DESC);

    }

}
