package net.bodz.bas.t.table;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.xml.xq.IElement;
import net.bodz.bas.json.JsonObject;

public class MutableTable
        extends RowList
        implements
            ITable {

    protected MutableTable() {
    }

    public MutableTable(ITableMetadata metadata) {
        super(metadata);
    }

    public MutableTable(ITableMetadata metadata, List<IRow> rows) {
        super(metadata, rows);
    }

    public MutableTable(ResultSet resultSet)
            throws SQLException {
        super(resultSet);
    }

    public MutableTable(ResultSet resultSet, Long maxRows)
            throws SQLException {
        super(resultSet, maxRows);
    }

    public static MutableTable fromTableElement(IElement x_table)
            throws ParseException, LoaderException {
        MutableTable o = new MutableTable();
        o.readObject(x_table);
        return o;
    }

    @Override
    public ISchema getParent() {
        return (ISchema) super.getParent();
    }

    @Override
    public void setParent(ITableMap parent) {
        ISchema parentSchema = (ISchema) parent;
        super.setParent(parentSchema);
    }

    @Override
    protected DefaultTableMetadata createMetadata() {
        return new DefaultTableMetadata();
    }

    @Override
    public ITableMetadata getMetadata() {
        return (ITableMetadata) super.getMetadata();
    }

    @Override
    public void readObject(JsonObject o)
            throws ParseException {
        boolean mergeMetadata = shouldMergeMetadata();

        if (mergeMetadata) {
            DefaultTableMetadata tmp = new DefaultTableMetadata();
            tmp.catalogName = o.getString(K_CATALOG_NAME);
            tmp.schemaName = o.getString(K_SCHEMA_NAME);
            tmp.tableName = o.getString(K_TABLE_NAME);
            this.metadata = tmp;
        }

        super.readObject(o);
    }

    @Override
    public void readObject(IElement x_table)
            throws ParseException, LoaderException {
        boolean mergeMetadata = shouldMergeMetadata();

        if (mergeMetadata) {
            DefaultTableMetadata tmp = new DefaultTableMetadata();
            tmp.catalogName = x_table.getAttribute(K_CATALOG_NAME);
            tmp.schemaName = x_table.getAttribute(K_SCHEMA_NAME);
            tmp.tableName = x_table.getAttribute(K_TABLE_NAME);
            this.metadata = tmp;
        }
        super.readObject(x_table);
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

    public String getPreparedInsert() {
        return getPreparedInsert(null);
    }

    public String getPreparedInsert(IRow row) {
        ITableMetadata metadata = getMetadata();
        StringBuilder ddl = new StringBuilder();
        ddl.append("insert into ");
        ddl.append(metadata.getName());
        ddl.append("(");

        StringBuilder vals = new StringBuilder();
        vals.append(") values(");

        int cc = metadata.getColumnCount();
        int paramIndex = 0;
        for (int columnIndex = 0; columnIndex < cc; columnIndex++) {
            if (row != null) {
                if (!row.isSet(columnIndex))
                    continue;
                Object val = row.get(columnIndex);
                if (val == null)
                    continue;
            }
            IColumnMetadata column = metadata.getColumn(columnIndex);
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
