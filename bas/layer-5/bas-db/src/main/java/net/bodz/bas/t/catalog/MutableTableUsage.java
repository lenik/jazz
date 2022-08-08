package net.bodz.bas.t.catalog;

import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.xml.xq.IElement;
import net.bodz.bas.json.JsonArray;
import net.bodz.bas.json.JsonObject;

public class MutableTableUsage
        implements
            ITableUsage {

    TableId tableId;
    List<String> columns = new ArrayList<>();

    public MutableTableUsage() {
    }

    public MutableTableUsage(TableId id) {
        this.tableId = id;
    }

    @Override
    public TableId getTableId() {
        return tableId;
    }

    public void setTableId(TableId tableId) {
        this.tableId = tableId;
    }

    @Override
    public List<String> getColumns() {
        return columns;
    }

    public void setColumns(List<String> columns) {
        this.columns = columns;
    }

    @Override
    public void readObject(JsonObject o)
            throws ParseException {
        tableId = new TableId();
        tableId.readObject(o);

        JsonArray o_columns = o.getJsonArray(K_COLUMNS);
        int n = o_columns.length();
        List<String> list = new ArrayList<>(n);
        for (int i = 0; i < n; i++)
            list.add(o_columns.getString(i));
        this.columns = list;
    }

    @Override
    public void readObject(IElement element)
            throws ParseException, LoaderException {
        tableId = new TableId();
        tableId.readObject(element);

        IElement x_columns = element.selectByTag(K_COLUMNS).first();
        List<String> list = new ArrayList<>();
        for (IElement x_column : x_columns.children()) {
            assert x_column.getTagName().equals(K_COLUMN);
            String column = x_columns.getTextContent();
            list.add(column);
        }
        this.columns = list;
    }

}
