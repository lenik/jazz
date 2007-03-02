package net.bodz.xml.xmod.modpdb;

import net.sf.freejava.util.Objects;

// Optimize: null indexes == all fields
public class FieldsPartial extends FieldsFull {

    protected final int[] indexes;

    public FieldsPartial(Table table, int... indexes) {
        super(table);
        assert indexes.length > 0;
        this.indexes = indexes;
    }

    public FieldsPartial(Table table, String... names) {
        super(table);
        assert names.length > 0;
        indexes = new int[names.length];
        for (int i = 0; i < names.length; i++) {
            indexes[i] = table.getFieldIndex(names[i]);
        }
    }

    public FieldsPartial(FieldsPartial base, int index1) {
        super(base.table);
        int baseLen = base.indexes.length;
        indexes = new int[baseLen + 1];
        System.arraycopy(base.indexes, 0, indexes, 0, baseLen);
        indexes[baseLen] = index1;
    }

    public FieldsPartial(FieldsPartial base, String name1) {
        this(base, base.table.getFieldIndex(name1));
    }

    public int size() {
        return indexes.length;
    }

    public Field get(int index) {
        Objects.checkIndex(indexes, index);
        return table.getField(indexes[index]);
    }

    public Object[] select(Object[] row) {
        return null;
    }

    public Object[] select(Row row) {
        if (table != null) {
            Table rowTable = row.getTable();
            if (!table.equals(rowTable))
                throw new IllegalArgumentException(
                        "Must select from a table of " + table);
        }
        return select(row.getCells());
    }

    public Row map(Row row) {
        return new RowPartial(table, indexes, select(row));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj == null)
            return false;
        if (obj instanceof FieldsPartial) {
            FieldsPartial b = (FieldsPartial) obj;
            if (!table.equals(b.table))
                return false;
            if (indexes.length != b.indexes.length)
                return false;
            for (int i = 0; i < indexes.length; i++) {
                // XXX - no-order
                if (indexes[i] != b.indexes[i])
                    return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = table.hashCode();
        for (int index : indexes) {
            hash <<= 1;
            hash ^= index;
        }
        return hash;
    }

    @Override
    public String toString() {
        if (repr == null) {
            StringBuffer buffer = new StringBuffer();
            buffer.append(table.getName());
            buffer.append('<');
            for (int off = 0; off < indexes.length; off++) {
                if (off > 0)
                    buffer.append(',');
                Field field = table.getField(indexes[off]);
                buffer.append(field.getName());
            }
            buffer.append('>');
            repr = buffer.toString();
        }
        return repr;
    }

}
