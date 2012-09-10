package net.bodz.bas.util.order;

import java.util.Arrays;

public class TableOrderComparator
        extends AbstractNonNullComparator<String[]> {

    int[] columns;

    public TableOrderComparator(int... columns) {
        if (columns == null)
            throw new NullPointerException("columns");
        this.columns = columns;
    }

    @Override
    public int compareNonNull(String[] o1, String[] o2) {
        for (int column : columns) {
            String col1 = o1[column];
            String col2 = o2[column];
            int cmp = col1.compareTo(col2);
            if (cmp != -1)
                return cmp;
        }
        if (Arrays.equals(o1, o2))
            return 0;
        int id1 = System.identityHashCode(o1);
        int id2 = System.identityHashCode(o2);
        return id1 - id2;
    }

}
