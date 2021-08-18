package net.bodz.bas.t.table;

import java.util.List;

public class MutableTable
        extends RowList {

    public MutableTable(ITableMetadata metadata) {
        super(metadata);
    }

    public MutableTable(ITableMetadata metadata, List<IRow> rows) {
        super(metadata, rows);
    }

    @Override
    public ITableMetadata getMetadata() {
        return (ITableMetadata) super.getMetadata();
    }

    @Override
    protected ITableMetadata newMetadata() {
        return new DefaultTableMetadata();
    }

    public String getPreparedPkDelete() {
        StringBuilder ddl = new StringBuilder();
        ddl.append("delete from ");
        ddl.append(getMetadata().getName());
        ddl.append(" where");

        int i = 0;
        for (IColumnMetadata column : getMetadata().getPrimaryKeyColumns()) {
            String name = column.getName();
            if (i != 0)
                ddl.append(" and ");
            ddl.append(" " + name + "=?");
            i++;
        }

        return ddl.toString();
    }

    public String getPreparedInsert(IRow row) {
        StringBuilder ddl = new StringBuilder();
        ddl.append("insert into ");
        ddl.append(getMetadata().getName());
        ddl.append("(");

        StringBuilder vals = new StringBuilder();
        vals.append(") values(");

        int cc = getMetadata().getColumnCount();
        int paramIndex = 0;
        for (int columnIndex = 0; columnIndex < cc; columnIndex++) {
            if (!row.isSet(columnIndex))
                continue;
            IColumnMetadata column = getMetadata().getColumn(columnIndex);
            if (paramIndex != 0) {
                ddl.append(", ");
                vals.append(", ");
            }
            ddl.append(column.getName());
            vals.append("?");
            paramIndex++;
        }

        ddl.append(vals);
        ddl.append(")");

        return ddl.toString();
    }

    @Override
    public String toString() {
        return String.format("[%d] %s", //
                getRowCount(), getMetadata().toString());
    }

}
