package net.bodz.bas.fmt.records;

import net.bodz.bas.c.object.Nullables;
import net.bodz.bas.t.order.AbstractNonNullComparator;

public class CsvRowComparator
        extends AbstractNonNullComparator<CsvRow> {

    int[] fieldIndices;

    public CsvRowComparator(int... fieldIndices) {
        this.fieldIndices = fieldIndices;
    }

    @Override
    public int compareNonNull(CsvRow o1, CsvRow o2) {
        for (int fieldIndex : fieldIndices) {
            boolean ascend = fieldIndex >= 0;
            if (!ascend)
                fieldIndex = -fieldIndex - 1;

            String field1 = o1.get(fieldIndex);
            String field2 = o2.get(fieldIndex);
            int cmp = Nullables.compare(field1, field2);
            if (cmp != 0)
                return cmp;
        }
        return o1 == o2 ? 0 : -1;
    }

    public static CsvRowComparator onFields(int... fieldIndices) {
        return new CsvRowComparator(fieldIndices);
    }

}
