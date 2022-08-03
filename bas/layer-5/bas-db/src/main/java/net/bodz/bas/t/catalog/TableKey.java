package net.bodz.bas.t.catalog;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Vector;

import javax.xml.stream.XMLStreamException;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.xml.IXmlForm;
import net.bodz.bas.fmt.xml.IXmlOutput;
import net.bodz.bas.fmt.xml.xq.IElement;
import net.bodz.bas.fmt.xml.xq.IElements;
import net.bodz.bas.json.JsonObject;

public final class TableKey
        implements
            IJsonForm,
            IXmlForm,
            Serializable {

    private static final long serialVersionUID = 1L;

    public static final String K_COLUMNS = "columns";
    public static final String K_COLUMN = "column";

    QualifiedTableName tableName;
    String[] columnNames;

    TableKey() {
    }

    public TableKey(QualifiedTableName table) {
        this.tableName = table;
    }

    public TableKey(QualifiedTableName table, String... columns) {
        this.tableName = table;
        this.columnNames = columns;
    }

    public TableKey(QualifiedTableName table, IColumnMetadata... columns) {
        this.tableName = table;
        this.columnNames = new String[columns.length];
        for (int i = 0; i < columns.length; i++)
            this.columnNames[i] = columns[i].getName();
    }

    public QualifiedTableName getTableName() {
        return tableName;
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

    public IColumnMetadata[] resolve(ITableMetadata table) {
        IColumnMetadata[] columns = new IColumnMetadata[columnNames.length];
        for (int i = 0; i < columnNames.length; i++) {
            columns[i] = table.getColumn(columnNames[i]);
        }
        return columns;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Objects.hash(tableName);
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
        return Arrays.equals(columnNames, other.columnNames) && Objects.equals(tableName, other.tableName);
    }

    @Override
    public String toString() {
        return tableName.getFullName() + "(" + getColumnNameList() + ")";
    }

    @Override
    public void readObject(JsonObject o)
            throws ParseException {
        tableName.readObject(o);
        columnNames = o.getStringArray(K_COLUMNS);
    }

    @Override
    public void writeObject(IJsonOut out)
            throws IOException, FormatException {
        tableName.writeObject(out);
        out.key(K_COLUMNS);
        out.array();
        for (String column : columnNames)
            out.value(column);
        out.endArray();
    }

    @Override
    public void readObject(IElement x_key)
            throws ParseException, LoaderException {
        tableName.readObject(x_key);
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
        tableName.writeObject(out);
        for (String column : columnNames)
            out.element(K_COLUMN, column);
    }

}
