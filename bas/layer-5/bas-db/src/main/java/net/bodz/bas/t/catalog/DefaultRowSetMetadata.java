package net.bodz.bas.t.catalog;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.bodz.bas.err.DuplicatedKeyException;
import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.fmt.xml.xq.IElement;
import net.bodz.bas.json.JsonArray;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.t.order.OrdinalComparator;

public class DefaultRowSetMetadata
        implements
            IRowSetMetadata {

    static final int MAX_SPARSE_COLUMNS = 1000;

    ISchemaMetadata parent;
    List<IColumnMetadata> columns = new ArrayList<>();
    Map<String, Integer> columnPosition = new LinkedHashMap<>();

    public DefaultRowSetMetadata() {
    }

    public DefaultRowSetMetadata(ISchemaMetadata parent) {
        this.parent = parent;
    }

    @Override
    public ISchemaMetadata getParent() {
        return parent;
    }

    public void setParent(ISchemaMetadata parent) {
        this.parent = parent;
    }

    @Override
    public Iterator<IColumnMetadata> iterator() {
        return columns.iterator();
    }

    @Override
    public boolean isSparse() {
        return false;
    }

    @Override
    public List<IColumnMetadata> getColumns() {
        return Collections.unmodifiableList(columns);
    }

    @Override
    public int getColumnCount() {
        return columns.size();
    }

    @Override
    public IColumnMetadata getColumn(int index) {
        if (index < 0)
            throw new IndexOutOfBoundsException("Invalid column index: " + index);
        if (index >= columns.size())
            throw new IndexOutOfBoundsException("Invalid column index: " + index);

        return columns.get(index);
    }

    @Override
    public synchronized IColumnMetadata getColumn(String name, boolean ignoreCase) {
        int pos;
        if (ignoreCase) {
            pos = indexOfColumn(name, true);
        } else {
            Integer ret = columnPosition.get(name);
            if (ret == null)
                pos = -1;
            else
                pos = ret.intValue();
        }
        if (pos == -1)
            return null;
        else
            return getColumn(pos);
    }

    public DefaultColumnMetadata newColumn() {
        DefaultColumnMetadata column = new DefaultColumnMetadata(this);
        addColumn(column);
        return column;
    }

    public synchronized void addColumn(IColumnMetadata column) {
        if (column == null)
            throw new NullPointerException("column");
        String name = column.getName();
        if (name == null)
            throw new NullPointerException("name");

        Integer lastPos = columnPosition.get(name);
        if (lastPos != null) {
            String err = String.format("Column[%s] was already added at position %d", name, lastPos);
            throw new DuplicatedKeyException(err);
        }

        int newPos = columns.size();
        columns.add(column);
        columnPosition.put(name, newPos);
    }

    public synchronized void setColumn(int index, IColumnMetadata column) {
        if (index < 0)
            throw new IndexOutOfBoundsException("invalid column index: " + index);

        int maxSize = columns.size();
        if (index >= maxSize)
            throw new IndexOutOfBoundsException("invalid column index: " + index);

        IColumnMetadata old = columns.get(index);
        assert old != null;
        String oldName = old.getName();
        int oldPosition = columnPosition.remove(oldName);
        assert oldPosition == index;

        columns.set(index, column);
        columnPosition.put(column.getName(), index);
    }

    public boolean removeColumn(IColumnMetadata column) {
        return removeColumn(column.getName());
    }

    public synchronized boolean removeColumn(String columnName) {
        Integer position = columnPosition.remove(columnName);
        if (position == null)
            return false;
        columns.remove(position.intValue());
        return true;
    }

    public synchronized void removeAllColumns() {
        columns.clear();
        columnPosition.clear();
    }

    public void sortColumns() {
        Collections.sort(this.columns, OrdinalComparator.INSTANCE);
    }

    @Override
    public void jsonIn(JsonObject o, JsonFormOptions opts)
            throws ParseException {
        JsonArray jv = o.getJsonArray(K_COLUMNS);
        int n = jv.length();
        removeAllColumns();
        for (int i = 0; i < n; i++) {
            JsonObject item = jv.getJsonObject(i);
            DefaultColumnMetadata column = new DefaultColumnMetadata(this);
            column.jsonIn(item, opts);
            addColumn(column);
        }
        sortColumns();
    }

    @Override
    public void readObject(IElement x_metadata)
            throws ParseException, LoaderException {
        IElement x_columns = x_metadata.selectByTag(K_COLUMNS).first();
        removeAllColumns();
        for (IElement x_column : x_columns.children()) {
            DefaultColumnMetadata column = new DefaultColumnMetadata(this);
            column.readObject(x_column);
            addColumn(column);
        }
        sortColumns();
    }

    public void loadFromRSMD(ResultSetMetaData rsmd)
            throws SQLException {
        int cc = rsmd.getColumnCount();
        for (int i = 1; i <= cc; i++) {
            DefaultColumnMetadata column = new DefaultColumnMetadata(this);
            column.setOrdinal(i);
            column.readObject(rsmd, i);
            addColumn(column);
        }
        sortColumns();
    }

    public String getColumnNames() {
        StringBuilder sb = new StringBuilder(columns.size() * 16);
        for (int i = 0; i < columns.size(); i++) {
            if (i != 0)
                sb.append(", ");
            IColumnMetadata column = columns.get(i);
            sb.append(column.getName());
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return getColumnNames();
    }

}
