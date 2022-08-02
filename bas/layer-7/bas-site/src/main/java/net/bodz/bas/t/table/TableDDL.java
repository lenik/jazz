package net.bodz.bas.t.table;

public class TableDDL {

    boolean ignoreCase;

    public TableDDL(boolean ignoreCase) {
        this.ignoreCase = ignoreCase;
    }

    public String deleteById(ITable table) {
        ITableMetadata metadata = table.getMetadata();
        StringBuilder sb = new StringBuilder();
        sb.append("delete from ");
        sb.append(metadata.getCompactName(ignoreCase));
        sb.append(" where");

        int i = 0;
        for (IColumnMetadata column : metadata.getPrimaryKeyColumns()) {
            String name = column.getName();
            if (i != 0)
                sb.append(" and ");
            sb.append(" " + name + "=?");
            i++;
        }
        return sb.toString();
    }

    public String insertFull(ITable table) {
        return insertForRow(table, null);
    }

    public String insertForRow(ITable table, IRow templateRow) {
        ITableMetadata metadata = table.getMetadata();
        StringBuilder p1 = new StringBuilder();
        p1.append("insert into ");
        p1.append(metadata.getName());
        p1.append("(");

        StringBuilder p2 = new StringBuilder();
        p2.append(") values(");

        int cc = metadata.getColumnCount();
        int paramIndex = 0;
        for (int columnIndex = 0; columnIndex < cc; columnIndex++) {
            if (templateRow != null) {
                if (!templateRow.isSet(columnIndex))
                    continue;
                Object val = templateRow.get(columnIndex);
                if (val == null)
                    continue;
            }
            IColumnMetadata column = metadata.getColumn(columnIndex);
            if (paramIndex != 0) {
                p1.append(", ");
                p2.append(", ");
            }
            p1.append(column.getName());
            p2.append("?");
            paramIndex++;
        }

        p1.append(p2);
        p1.append(")");

        return p1.toString();
    }

    public static final TableDDL STD = new TableDDL(false);
    public static final TableDDL NO_CASE = new TableDDL(true);

}
