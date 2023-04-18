package net.bodz.bas.t.catalog;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.xml.stream.XMLStreamException;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.fmt.xml.IXmlForm;
import net.bodz.bas.fmt.xml.IXmlOutput;
import net.bodz.bas.fmt.xml.xq.IElement;
import net.bodz.bas.json.JsonObject;

public class ColumnOid
        implements
            IJsonForm,
            IXmlForm,
            Serializable {

    private static final long serialVersionUID = 1L;

    public static final String K_TABLE = "table";
    public static final String K_COLUMN_NAME = "columnName";

    protected TableOid table;
    protected String columnName;

    public ColumnOid() {
        table = new TableOid();
    }

    public ColumnOid(TableOid table, String columnName) {
        if (table == null)
            throw new NullPointerException("table");
        this.table = table;
        this.columnName = columnName;
    }

    public static ColumnOid parse(String fullName) {
        ColumnOid o = new ColumnOid();
        o.setFullName(fullName);
        return o;
    }

    public TableOid getTable() {
        return table;
    }

    public void setTable(TableOid table) {
        this.table = table;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public void clear() {
        this.table.clear();
        this.columnName = null;
    }

    public void assign(TableOid table, String columnName) {
        if (table == null)
            throw new NullPointerException("table");
        this.table = table;
        this.columnName = columnName;
    }

    protected StringBuilder buildFullName() {
        StringBuilder sb = table.buildFullName();
        sb.append('.');
        sb.append(columnName);
        return sb;
    }

    public String getFullName() {
        return buildFullName().toString();
    }

    public void setFullName(String s) {
        int dot = s.lastIndexOf('.');
        if (dot == -1)
            throw new IllegalArgumentException("table name isn't specified.");
        String qTable = s.substring(0, dot);
        table.setFullName(qTable);
        columnName = s.substring(dot + 1);
    }

    public String getCompactName(TableOid contextTable) {
        return table.getCompactName(contextTable) + "." + columnName;
    }

    public String getCompactName(boolean ignoreCase, TableOid contextTable) {
        return table.getCompactName(ignoreCase, contextTable.toSchemaId()) + "." + columnName;
    }

    public String getCompactName(SchemaOid... searchSchemas) {
        return getCompactName(false, searchSchemas);
    }

    public String getCompactName(boolean ignoreCase, SchemaOid... searchSchemas) {
        if (searchSchemas == null)
            return getFullName();
        String compactName = table.getCompactName(ignoreCase, searchSchemas);
        return compactName + "." + compactName;
    }

    public String getPreferredPackageName(String parentPackageName) {
        if (parentPackageName == null)
            throw new NullPointerException("parentPackageName");
        String packageName = table.getPreferredPackageName(parentPackageName);
        return packageName + "." + columnName;
    }

    public boolean contains(ColumnOid o, boolean ignoreCase) {
        if (ignoreCase)
            return containsIgnoreCase(o);
        else
            return contains(o);
    }

    public boolean contains(ColumnOid o) {
        if (!table.contains(o.table))
            return false;
        if (!NamePattern.matches(columnName, o.columnName))
            return false;
        return true;
    }

    public boolean containsIgnoreCase(ColumnOid o) {
        if (!table.containsIgnoreCase(o.table))
            return false;
        if (!NamePattern.matchesIgnoreCase(columnName, o.columnName))
            return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder fullName = buildFullName();
        return fullName.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(table, columnName);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ColumnOid other = (ColumnOid) obj;
        return Objects.equals(table, other.table) //
                && Objects.equals(columnName, other.columnName);
    }

    @Override
    public void jsonIn(JsonObject o, JsonFormOptions opts)
            throws ParseException {
        JsonObject j_table = o.getJsonObject(K_TABLE);
        if (j_table != null)
            table.jsonIn(j_table);
        columnName = o.getString(K_COLUMN_NAME);
    }

    @Override
    public void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException, FormatException {
        if (table != null) {
            out.key(K_TABLE);
            out.object();
            table.jsonOut(out, opts);
            out.endObject();
        }
        out.entry(K_COLUMN_NAME, columnName);
    }

    @Override
    public void readObject(IElement element)
            throws ParseException, LoaderException {
        // TODO
        columnName = element.getAttribute(K_COLUMN_NAME);
    }

    @Override
    public void writeObject(IXmlOutput out)
            throws XMLStreamException, FormatException {
        out.attributeNotNull(K_COLUMN_NAME, columnName);
    }

//    @Override
//    public void readFromJDBC(ResultSet rs)
//            throws SQLException {
//        table.readFromJDBC(rs);
//        columnName = rs.getString("COL_NAME"); // TODO check if correct...?
//    }

    public static List<ColumnOid> cut(List<? extends IColumnMetadata> list) {
        List<ColumnOid> ids = new ArrayList<>(list.size());
        for (IColumnMetadata column : list)
            ids.add(column.getId());
        return ids;
    }

}
