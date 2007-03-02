package net.bodz.xml.xmod.modpdb;

public class FieldsFull implements Fields {

    protected final Table table;

    public FieldsFull(Table table) {
        assert table != null;
        this.table = table;
    }

    public int size() {
        return table.sizeFields();
    }

    public Field get(int index) {
        return table.getField(index);
    }

    public Object[] select(Object[] row) {
        int size = table.sizeFields();
        Object[] selected = new Object[size];
        for (int i = 0; i < size; i++)
            selected[i] = row[i];
        return selected;
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
        return new RowFull(table, select(row));
    }

    protected String repr = null;

    @Override
    public String toString() {
        if (repr == null) {
            StringBuffer buffer = new StringBuffer();
            buffer.append(table.getName());
            buffer.append('<');
            int n = table.sizeFields();
            for (int i = 0; i < n; i++) {
                if (i > 0)
                    buffer.append(',');
                Field field = table.getField(i);
                buffer.append(field.getName());
            }
            buffer.append('>');
            repr = buffer.toString();
        }
        return repr;
    }

}
