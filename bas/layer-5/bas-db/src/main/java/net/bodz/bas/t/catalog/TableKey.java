package net.bodz.bas.t.catalog;

import java.io.IOException;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javax.xml.stream.XMLStreamException;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.NoSuchKeyException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.fmt.xml.IXmlForm;
import net.bodz.bas.fmt.xml.IXmlOutput;
import net.bodz.bas.fmt.xml.xq.IElement;
import net.bodz.bas.fmt.xml.xq.IElements;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.t.map.ListMap;

public final class TableKey
        implements
            IJsonForm,
            IXmlForm,
            Serializable {

    private static final long serialVersionUID = 1L;

    public static final String K_COLUMNS = "columns";
    public static final String K_COLUMN = "column";

    TableOid oid;
    String[] columnNames;

    TableKey() {
    }

    public TableKey(TableOid oid) {
        this.oid = oid;
    }

    public TableKey(TableOid table, String... columns) {
        this.oid = table;
        this.columnNames = columns;
    }

    public TableKey(TableOid table, IColumnMetadata... columns) {
        this.oid = table;
        this.columnNames = new String[columns.length];
        for (int i = 0; i < columns.length; i++)
            this.columnNames[i] = columns[i].getName();
    }

    public TableOid getId() {
        return oid;
    }

    public String[] getColumnNames() {
        return columnNames;
    }

    public List<String> getColumnNameList() {
        return Arrays.asList(columnNames);
    }

    public Vector<String> getColumnNameVector() {
        Vector<String> vector = new Vector<>(columnNames.length);
        for (String c : columnNames)
            vector.add(c);
        return vector;
    }

    public boolean contains(String columnName) {
        if (columnName == null)
            throw new NullPointerException("columnName");
        return indexOf(columnName) != -1;
    }

    public int indexOf(String columnName) {
        if (columnName == null)
            throw new NullPointerException("columnName");
        for (int i = 0; i < columnNames.length; i++)
            if (columnNames[i].equals(columnName))
                return i;
        return -1;
    }

    public boolean containsIgnoreCase(String columnName) {
        if (columnName == null)
            throw new NullPointerException("columnName");
        return indexOfIgnoreCase(columnName) != -1;
    }

    public int indexOfIgnoreCase(String columnName) {
        if (columnName == null)
            throw new NullPointerException("columnName");
        for (int i = 0; i < columnNames.length; i++)
            if (columnNames[i].equalsIgnoreCase(columnName))
                return i;
        return -1;
    }

    public IColumnMetadata[] resolve(ITableViewMetadata table) {
        IColumnMetadata[] columns = new IColumnMetadata[columnNames.length];
        for (int i = 0; i < columnNames.length; i++) {
            columns[i] = table.getColumn(columnNames[i]);
        }
        return columns;
    }

    /**
     * @throws NoSuchKeyException
     */
    public int[] resolvePosition(ITableViewMetadata table) {
        return resolvePosition(table, false);
    }

    /**
     * @param opt
     *            Use <code>-1</code> for unknown column. <code>false</code> will raise
     *            {@link NoSuchKeyException} exception.
     * @throws NoSuchKeyException
     */
    public int[] resolvePosition(ITableViewMetadata table, boolean opt) {
        int[] posv = new int[columnNames.length];
        for (int i = 0; i < columnNames.length; i++) {
            IColumnMetadata column = table.getColumn(columnNames[i]);
            posv[i] = opt ? column.getPositionOpt() : column.position();
        }
        return posv;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Objects.hash(oid);
        result = prime * result + Arrays.hashCode(columnNames);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TableKey other = (TableKey) obj;
        return Arrays.equals(columnNames, other.columnNames) && Objects.equals(oid, other.oid);
    }

    @Override
    public String toString() {
        return oid.getFullName() + "(" + getColumnNameList() + ")";
    }

    @Override
    public void jsonIn(JsonObject o, JsonFormOptions opts)
            throws ParseException {
        oid.jsonIn(o, opts);
        columnNames = o.getStringArray(K_COLUMNS);
    }

    @Override
    public void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException, FormatException {
        oid.jsonOut(out, opts);
        out.key(K_COLUMNS);
        out.array();
        for (String column : columnNames)
            out.value(column);
        out.endArray();
    }

    @Override
    public void readObject(IElement x_key)
            throws ParseException, LoaderException {
        oid.readObject(x_key);
        IElements x_column_v = x_key.children();
        int n = x_column_v.getElementCount();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            IElement x_column = x_column_v.get(i);
            assert x_column.getTagName().equals(K_COLUMN);
            String column = x_column.getTextContent(); // .trim();
            list.add(column);
        }
        this.columnNames = list.toArray(new String[0]);
    }

    @Override
    public void writeObject(IXmlOutput out)
            throws XMLStreamException, FormatException {
        oid.writeObject(out);
        for (String column : columnNames)
            out.element(K_COLUMN, column);
    }

    public static Map<TableOid, TableKey> convertFromJDBC(ResultSet rs)
            throws SQLException {
        ListMap<TableOid, String> map = new ListMap<>(); // SortOrder.KEEP
        while (rs.next()) {
            TableOid tableName = new TableOid();
            tableName.readFromJDBC(rs);

            String column = rs.getString("COLUMN_NAME");
            List<String> list = map.getOrCreate(tableName);
            list.add(column);
        }

        Map<TableOid, TableKey> conv = new HashMap<>();
        for (TableOid tableName : map.keySet()) {
            List<String> list = map.get(tableName);
            String[] columns = list.toArray(new String[0]);
            TableKey primaryKey = new TableKey(tableName, columns);
            conv.put(tableName, primaryKey);
        }
        return conv;
    }

}
