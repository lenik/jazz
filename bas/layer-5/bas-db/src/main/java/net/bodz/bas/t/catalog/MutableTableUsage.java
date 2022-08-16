package net.bodz.bas.t.catalog;

import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.fmt.xml.xq.IElement;
import net.bodz.bas.json.JsonArray;
import net.bodz.bas.json.JsonObject;

public class MutableTableUsage
        implements
            ITableUsage {

    TableOid tableId;
    List<String> columns = new ArrayList<>();

    public MutableTableUsage() {
    }

    public MutableTableUsage(TableOid oid) {
        this.tableId = oid;
    }

    @Override
    public TableOid getTableId() {
        return tableId;
    }

    public void setTableId(TableOid tableId) {
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
    public void jsonIn(JsonObject o, JsonFormOptions opts)
            throws ParseException {
        tableId = new TableOid();
        tableId.jsonIn(o, opts);

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
        tableId = new TableOid();
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
