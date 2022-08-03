package net.bodz.bas.t.catalog;

import net.bodz.bas.t.order.AbstractNonNullComparator;

public class QualifiedTableNameComparator
        extends AbstractNonNullComparator<QualifiedTableName> {

    int sign;

    public QualifiedTableNameComparator(int sign) {
        this.sign = sign;
    }

    @Override
    public int compareNonNull(QualifiedTableName o1, QualifiedTableName o2) {
        int cmp = compareString(o1.catalogName, o2.catalogName);
        if (cmp != 0)
            return cmp * sign;
        cmp = compareString(o1.schemaName, o2.schemaName);
        if (cmp != 0)
            return cmp * sign;
        cmp = compareString(o1.tableName, o2.tableName);
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

    public static final QualifiedTableNameComparator ASC = new QualifiedTableNameComparator(1);
    public static final QualifiedTableNameComparator DESC = new QualifiedTableNameComparator(-1);

    public static class CaseInsensitive
            extends QualifiedTableNameComparator {

        public CaseInsensitive(int sign) {
            super(sign);
        }

        @Override
        protected int compareNonNullString(String a, String b) {
            return a.compareToIgnoreCase(b);
        }

        public static final QualifiedTableNameComparator ASC = new CaseInsensitive(1);
        public static final QualifiedTableNameComparator DESC = new CaseInsensitive(-1);

    }

}
