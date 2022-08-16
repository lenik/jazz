package net.bodz.bas.t.catalog;

import net.bodz.bas.t.order.AbstractNonNullComparator;

public class SchemaOidComparator
        extends AbstractNonNullComparator<SchemaOid> {

    int sign;

    public SchemaOidComparator(int sign) {
        this.sign = sign;
    }

    @Override
    public int compareNonNull(SchemaOid o1, SchemaOid o2) {
        int cmp = compareString(o1.catalogName, o2.catalogName);
        if (cmp != 0)
            return cmp * sign;
        cmp = compareString(o1.schemaName, o2.schemaName);
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

    public static final SchemaOidComparator ASC = new SchemaOidComparator(1);
    public static final SchemaOidComparator DESC = new SchemaOidComparator(-1);

    public static class CaseInsensitive
            extends SchemaOidComparator {

        public CaseInsensitive(int sign) {
            super(sign);
        }

        @Override
        protected int compareNonNullString(String a, String b) {
            return a.compareToIgnoreCase(b);
        }

        public static final SchemaOidComparator ASC = new CaseInsensitive(1);
        public static final SchemaOidComparator DESC = new CaseInsensitive(-1);

    }

}
