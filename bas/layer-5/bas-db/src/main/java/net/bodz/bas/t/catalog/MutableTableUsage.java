package net.bodz.bas.t.catalog;

import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.fmt.xml.xq.IElement;
import net.bodz.bas.json.JsonArray;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.meta.decl.NotNull;

public class MutableTableUsage
        implements
            ITableUsage {

    List<String> viewColumns = new ArrayList<>();

    TableOid tableId;
    List<String> fromColumns = new ArrayList<>();

    public MutableTableUsage() {
    }

    public MutableTableUsage(TableOid oid) {
        this.tableId = oid;
    }

    @Override
    public List<String> getViewColumns() {
        return viewColumns;
    }

    public void setViewColumns(List<String> columns) {
        this.viewColumns = columns;
    }

    @Override
    public TableOid getFromTableId() {
        return tableId;
    }

    public void setFromTableId(TableOid tableId) {
        this.tableId = tableId;
    }

    @Override
    public List<String> getFromColumns() {
        return fromColumns;
    }

    public void setFromColumns(List<String> columns) {
        this.fromColumns = columns;
    }

    @Override
    public void jsonIn(@NotNull JsonObject o, JsonFormOptions opts)
            throws ParseException {
        tableId = new TableOid();
        tableId.jsonIn(o, opts);

        JsonArray o_viewColumns = o.getJsonArray(K_VIEW_COLUMNS);
        int n = o_viewColumns.length();
        List<String> viewList = new ArrayList<>(n);
        for (int i = 0; i < n; i++)
            viewList.add(o_viewColumns.getString(i));
        this.viewColumns = viewList;

        JsonArray o_fromColumns = o.getJsonArray(K_FROM_COLUMNS);
        n = o_fromColumns.length();
        List<String> fromList = new ArrayList<>(n);
        for (int i = 0; i < n; i++)
            fromList.add(o_fromColumns.getString(i));
        this.fromColumns = fromList;
    }

    @Override
    public void readObject(IElement element)
            throws ParseException, LoaderException {
        tableId = new TableOid();
        tableId.readObject(element);

        IElement x_viewColumns = element.selectByTag(K_VIEW_COLUMNS).first();
        List<String> viewList = new ArrayList<>();
        for (IElement x_column : x_viewColumns.children()) {
            assert x_column.getTagName().equals(K_VIEW_COLUMN);
            String column = x_viewColumns.getTextContent();
            viewList.add(column);
        }
        this.viewColumns = viewList;

        IElement x_fromColumns = element.selectByTag(K_FROM_COLUMNS).first();
        List<String> fromList = new ArrayList<>();
        for (IElement x_column : x_fromColumns.children()) {
            assert x_column.getTagName().equals(K_FROM_COLUMN);
            String column = x_fromColumns.getTextContent();
            fromList.add(column);
        }
        this.fromColumns = fromList;
    }

}
