package net.bodz.bas.t.catalog;

import net.bodz.bas.t.order.AbstractNonNullComparator;

public class TableKeyComparator
        extends AbstractNonNullComparator<TableKey> {

    int sign;
    TableOidComparator idCmp;

    public TableKeyComparator(int sign, TableOidComparator idCmp) {
        this.sign = sign;
        this.idCmp = idCmp;
    }

    @Override
    public int compareNonNull(TableKey o1, TableKey o2) {
        int cmp = idCmp.compare(o1.oid, o2.oid);
        if (cmp != 0)
            return cmp * sign;

        if (o1.columnNames == o2.columnNames)
            return 0;
        if (o1.columnNames == null)
            return -1;
        if (o2.columnNames == null)
            return 1;
        int min = Math.min(o1.columnNames.length, o2.columnNames.length);
        for (int i = 0; i < min; i++) {
            String c1 = o1.columnNames[i];
            String c2 = o2.columnNames[i];
            cmp = compareString(c1, c2);
            if (cmp != 0)
                return cmp * sign;
        }
        cmp = o1.columnNames.length - o2.columnNames.length;
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

    public static final TableKeyComparator ASC = new TableKeyComparator(1, TableOidComparator.ASC);
    public static final TableKeyComparator DESC = new TableKeyComparator(-1, TableOidComparator.DESC);

    public static class CaseInsensitive
            extends TableKeyComparator {

        public CaseInsensitive(int sign, TableOidComparator tableCmp) {
            super(sign, tableCmp);
        }

        @Override
        protected int compareNonNullString(String a, String b) {
            return a.compareToIgnoreCase(b);
        }

        public static final TableKeyComparator ASC = new CaseInsensitive(1, TableOidComparator.CaseInsensitive.ASC);
        public static final TableKeyComparator DESC = new CaseInsensitive(-1, TableOidComparator.CaseInsensitive.DESC);

    }

}
