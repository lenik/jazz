package net.bodz.bas.t.catalog;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.xml.stream.XMLStreamException;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.fmt.xml.IXmlOutput;
import net.bodz.bas.fmt.xml.xq.IElement;
import net.bodz.bas.json.JsonObject;

public class TableOid
        extends CSNamePair {

    private static final long serialVersionUID = 1L;

    public static final String K_TABLE_NAME = "tableName";

    protected String tableName;

    public TableOid() {
    }

    public TableOid(String catalogName, String schemaName, String tableName) {
        super(catalogName, schemaName);
        this.tableName = tableName;
    }

    public static TableOid parse(String fullName) {
        TableOid o = new TableOid();
        o.setFullName(fullName);
        return o;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Override
    public void clear() {
        super.clear();
        this.tableName = null;
    }

    public void assign(String catalogName, String schemaName, String tableName) {
        this.catalogName = catalogName;
        this.schemaName = schemaName;
        this.tableName = tableName;
    }

    @Override
    protected StringBuilder buildFullName() {
        StringBuilder sb = super.buildFullName();
        if (sb.length() != 0)
            sb.append('.');
        sb.append(tableName);
        return sb;
    }

    @Override
    public void setFullName(String s) {
        int dot = s.lastIndexOf('.');
        if (dot != -1) {
            String cs = s.substring(0, dot);
            super.setFullName(cs);
            s = s.substring(dot + 1);
        }
        tableName = s;
    }

    public String getCompactName(TableOid contextTable) {
        return getCompactName(contextTable.toSchemaId());
    }

    public String getCompactName(boolean ignoreCase, TableOid contextTable) {
        return getCompactName(ignoreCase, contextTable.toSchemaId());
    }

    public String getCompactName(SchemaOid... searchSchemas) {
        return getCompactName(false, searchSchemas);
    }

    public String getCompactName(boolean ignoreCase, SchemaOid... searchSchemas) {
        if (searchSchemas == null)
            return getFullName();
        StringBuilder sb = new StringBuilder();
        if (isCatalogNameNeeded(ignoreCase, searchSchemas)) {
            sb.append(catalogName);
            sb.append('.');
        }
        if (isSchemaSpecified(ignoreCase, searchSchemas)) {
            sb.append(schemaName);
            sb.append('.');
        }
        sb.append(tableName);
        return sb.toString();
    }

    public String getPreferredPackageName(String parentPackageName) {
        if (parentPackageName == null)
            throw new NullPointerException("parentPackageName");
        StringBuilder sb = new StringBuilder(parentPackageName);
        if (catalogName != null) {
            sb.append('.');
            sb.append(catalogName);
        }
        if (schemaName != null) {
            sb.append('.');
            sb.append(schemaName);
        }
        String packageName = sb.toString();
        return packageName;
    }

    public boolean contains(TableOid o, boolean ignoreCase) {
        if (ignoreCase)
            return containsIgnoreCase(o);
        else
            return contains(o);
    }

    public boolean contains(TableOid o) {
        if (! NamePattern.matches(catalogName, o.catalogName))
            return false;
        if (! NamePattern.matches(schemaName, o.schemaName))
            return false;
        if (! NamePattern.matches(tableName, o.tableName))
            return false;
        return true;
    }

    public boolean containsIgnoreCase(TableOid o) {
        if (! NamePattern.matchesIgnoreCase(catalogName, o.catalogName))
            return false;
        if (! NamePattern.matchesIgnoreCase(schemaName, o.schemaName))
            return false;
        if (! NamePattern.matchesIgnoreCase(tableName, o.tableName))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(catalogName, schemaName, tableName);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TableOid other = (TableOid) obj;
        return Objects.equals(catalogName, other.catalogName) //
                && Objects.equals(schemaName, other.schemaName) //
                && Objects.equals(tableName, other.tableName);
    }

    @Override
    public void jsonIn(JsonObject o, JsonFormOptions opts)
            throws ParseException {
        super.jsonIn(o, opts);
        tableName = o.getString(K_TABLE_NAME);
    }

    @Override
    public void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException, FormatException {
        super.jsonOut(out, opts);
        if (tableName != null)
            out.entry(K_TABLE_NAME, tableName);
    }

    @Override
    public void readObject(IElement element)
            throws ParseException, LoaderException {
        super.readObject(element);
        tableName = element.getAttribute(K_TABLE_NAME);
    }

    @Override
    public void writeObject(IXmlOutput out)
            throws XMLStreamException, FormatException {
        super.writeObject(out);
        if (tableName != null)
            out.attribute(K_TABLE_NAME, tableName);
    }

    @Override
    public void readFromJDBC(ResultSet rs)
            throws SQLException {
        super.readFromJDBC(rs);
        tableName = rs.getString("TABLE_NAME");
    }

    public static List<TableOid> cut(List<? extends ITableMetadata> list) {
        List<TableOid> ids = new ArrayList<>(list.size());
        for (ITableMetadata table : list)
            ids.add(table.getId());
        return ids;
    }

}
