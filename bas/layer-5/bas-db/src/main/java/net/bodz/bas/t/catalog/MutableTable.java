package net.bodz.bas.t.catalog;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.xml.xq.IElement;
import net.bodz.bas.fmt.xml.xq.IElements;
import net.bodz.bas.json.JsonArray;
import net.bodz.bas.json.JsonObject;

public class MutableTable
        extends RowList
        implements
            ITable {

    ISchema parent;
    TableId id;

    protected MutableTable() {
    }

    public MutableTable(ITableMetadata metadata) {
        super(metadata);
    }

    public MutableTable(ITableMetadata metadata, List<IRow> rows) {
        super(metadata, rows);
    }

    public static MutableTable fromTableElement(IElement x_table)
            throws ParseException, LoaderException {
        MutableTable o = new MutableTable();
        o.readObject(x_table);
        return o;
    }

    public static MutableTable fromResultSet(ResultSet resultSet)
            throws SQLException {
        return fromResultSet(resultSet, null);
    }

    public static MutableTable fromResultSet(ResultSet resultSet, Long limit)
            throws SQLException {
        DefaultTableMetadata metadata = new DefaultTableMetadata();
        metadata.loadFromRSMD(resultSet.getMetaData());
        MutableTable table = new MutableTable(metadata);
        table.consume(resultSet, limit);
        return table;
    }

    @Override
    public ITableMetadata getMetadata() {
        return (ITableMetadata) super.getMetadata();
    }

    @Override
    protected DefaultTableMetadata createMetadata() {
        return new DefaultTableMetadata();
    }

    @Override
    public ISchema getParent() {
        return parent;
    }

    public void setParent(ISchema parent) {
        this.parent = parent;
    }

    @Override
    public TableId getId() {
        if (id != null)
            return id;
        ITableMetadata metadata = getMetadata();
        if (metadata != null)
            return metadata.getId();
        return null;
    }

    public void setId(TableId id) {
        this.id = id;
    }

    @Override
    public void readObject(JsonObject o)
            throws ParseException {
        IRowSetMetadata metadata = getMetadata();
        if (isAttached()) {
            this.id = new TableId();
            this.id.readObject(o);
        } else {
            JsonObject j_md = o.getJsonObject(K_METADATA);
            if (j_md != null) {
                metadata = createMetadata();
                metadata.readObject(j_md);
                this.metadata = metadata;
            }
        }

        JsonArray j_rows = o.getJsonArray(K_ROWS);
        int n = j_rows.length();
        List<IRow> rows = new ArrayList<>();
        for (int rowIndex = 0; rowIndex < n; rowIndex++) {
            JsonArray j_row = j_rows.getJsonArray(rowIndex);
            MutableRow row = new MutableRow(this, rowIndex);
            row.readObjectBoxed(j_row);
            rows.add(row);
        }
        this.rows = rows;
    }

    @Override
    public void readObject(IElement x_table)
            throws ParseException, LoaderException {
        IRowSetMetadata metadata = getMetadata();
        if (isAttached()) {
            this.id = new TableId();
            this.id.readObject(x_table);
        } else {
            IElement x_md = x_table.selectByTag(K_METADATA).getFirst();
            if (x_md != null && x_md.getParentNode() == x_table) {
                metadata = createMetadata();
                metadata.readObject(x_md);
                this.metadata = metadata;
            }
        }

        IElement x_rows = x_table.selectByTag(K_ROWS).getFirst();
        IElements x_row_v = x_rows.children(); // <row>
        int rowCount = x_rows.getElementCount();
        List<IRow> rows = new ArrayList<>();
        for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
            IElement x_row = x_row_v.get(rowIndex);
            MutableRow row = new MutableRow(this, rowIndex);
            row.readObject(x_row);
            rows.add(row);
        }
        this.rows = rows;
    }

    @Override
    public String toString() {
        return String.format("[%d] %s", //
                getRowCount(), getMetadata().toString());
    }

}
