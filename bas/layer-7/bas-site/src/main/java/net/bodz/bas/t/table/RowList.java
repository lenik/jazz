package net.bodz.bas.t.table;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.xml.xq.IElement;
import net.bodz.bas.fmt.xml.xq.IElements;
import net.bodz.bas.json.JsonArray;
import net.bodz.bas.json.JsonObject;

public class RowList
        implements
            IRowSet {

    IRowSetMetadata metadata;
    List<IRow> rows;

    private RowList(IRowSetMetadata metadata, List<IRow> rows, boolean copy) {
        if (metadata == null)
            throw new NullPointerException("metadata");
        if (rows == null)
            throw new NullPointerException("rows");

        this.metadata = metadata;
        if (copy)
            this.rows = new ArrayList<>(rows);
        else
            this.rows = rows;
    }

    public RowList(List<IRow> rows, IRowSetMetadata metadata) {
        this(metadata, rows, true);
    }

    public static RowList wrap(List<IRow> rows, IRowSetMetadata metadata) {
        return new RowList(metadata, rows, false);
    }

    @Override
    public IRowSetMetadata getMetadata() {
        return metadata;
    }

    @Override
    public int getRowCount() {
        return rows.size();
    }

    @Override
    public Iterator<IRow> iterator() {
        return rows.iterator();
    }

    @Override
    public void readObject(JsonObject o)
            throws ParseException {
        JsonObject j_md = o.getJsonObject("metadata");
        if (j_md != null) {
            DefaultRowSetMetadata metadata = new DefaultRowSetMetadata();
            metadata.readObject(j_md);
            this.metadata = metadata;
        }

        JsonArray j_rows = o.getJsonArray("rows");
        int n = j_rows.length();
        List<IRow> rows = new ArrayList<>();
        for (int rowIndex = 0; rowIndex < n; rowIndex++) {
            JsonArray j_row = j_rows.getJsonArray(rowIndex);
            MutableRow row = new MutableRow(metadata, rowIndex);
            row.readObjectBoxed(j_row);
            rows.add(row);
        }
        this.rows = rows;
    }

    @Override
    public void readObject(IElement x_table)
            throws ParseException, LoaderException {
        IElement x_md = x_table.selectByTag("metadata").getFirst();
        if (x_md != null && x_md.getParentNode() == x_table) {
            DefaultRowSetMetadata metadata = new DefaultRowSetMetadata();
            metadata.readObject(x_md);
            this.metadata = metadata;
        }

        IElement x_rows = x_table.selectByTag("rows").getFirst();
        IElements x_row_v = x_rows.children(); // <row>
        int rowCount = x_rows.getElementCount();
        List<IRow> rows = new ArrayList<>();
        for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
            IElement x_row = x_row_v.get(rowIndex);
            MutableRow row = new MutableRow(metadata, rowIndex);
            row.readObject(x_row);
            rows.add(row);
        }
        this.rows = rows;
    }

}
