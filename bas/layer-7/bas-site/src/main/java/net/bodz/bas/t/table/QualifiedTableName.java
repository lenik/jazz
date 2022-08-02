package net.bodz.bas.t.table;

import java.io.IOException;
import java.io.Serializable;
import java.util.Objects;

import javax.xml.stream.XMLStreamException;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.xml.IXmlForm;
import net.bodz.bas.fmt.xml.IXmlOutput;
import net.bodz.bas.fmt.xml.xq.IElement;
import net.bodz.bas.json.JsonObject;

public class QualifiedTableName
        implements
            IJsonForm,
            IXmlForm,
            Serializable {

    private static final long serialVersionUID = 1L;

    public static final String K_CATALOG_NAME = "catalogName";
    public static final String K_SCHEMA_NAME = "schemaName";
    public static final String K_TABLE_NAME = "tableName";

    protected String catalogName;
    protected String schemaName;
    protected String tableName;

    public QualifiedTableName() {
    }

    public QualifiedTableName(String catalogName, String schemaName, String tableName) {
        this.catalogName = catalogName;
        this.schemaName = schemaName;
        this.tableName = tableName;
    }

    public static QualifiedTableName parse(String fullName) {
        QualifiedTableName o = new QualifiedTableName();
        o.parseFullName(fullName);
        return o;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public String getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void clear() {
        this.catalogName = null;
        this.schemaName = null;
        this.tableName = null;
    }

    public void assign(String catalogName, String schemaName, String tableName) {
        this.catalogName = catalogName;
        this.schemaName = schemaName;
        this.tableName = tableName;
    }

    public String getFullName() {
        StringBuilder sb = new StringBuilder();
        if (catalogName != null) {
            sb.append(catalogName);
            sb.append('.');
        }
        if (schemaName != null) {
            sb.append(schemaName);
            sb.append('.');
        }
        sb.append(tableName);
        return sb.toString();
    }

    public void parseFullName(String name) {
        int dot = name.lastIndexOf('.');
        if (dot != -1) {
            String schema = name.substring(0, dot);
            name = name.substring(dot + 1);
            dot = schema.lastIndexOf('.');
            if (dot != -1) {
                String catalog = schema.substring(0, dot);
                schema = schema.substring(dot + 1);
                this.catalogName = catalog;
            }
            this.schemaName = schema;
        }
        this.tableName = name;
    }

    public boolean isCatalogSpecified(QualifiedTableName orig) {
        return isCatalogSpecified(orig, false);
    }

    public boolean isCatalogSpecified(QualifiedTableName orig, boolean ignoreCase) {
        if (catalogName == null)
            return false;
        if (orig.catalogName != null)
            if (ignoreCase) {
                if (catalogName.equalsIgnoreCase(orig.catalogName))
                    return false;
            } else {
                if (catalogName.equals(orig.catalogName))
                    return false;
            }
        return true;
    }

    public boolean isSchemaSpecified(QualifiedTableName orig) {
        return isSchemaSpecified(orig, false);
    }

    public boolean isSchemaSpecified(QualifiedTableName orig, boolean ignoreCase) {
        if (schemaName == null)
            return false;
        if (orig.schemaName != null)
            if (ignoreCase) {
                if (schemaName.equalsIgnoreCase(orig.schemaName))
                    return false;
            } else {
                if (schemaName.equals(orig.schemaName))
                    return false;
            }
        return true;
    }

    public String getCompactName(QualifiedTableName orig) {
        return getCompactName(orig, false);
    }

    public String getCompactName(QualifiedTableName orig, boolean ignoreCase) {
        if (orig == null)
            return getFullName();
        StringBuilder sb = new StringBuilder();
        if (isCatalogSpecified(orig, ignoreCase)) {
            sb.append(catalogName);
            sb.append('.');
        }
        if (isSchemaSpecified(orig, ignoreCase)) {
            sb.append(schemaName);
            sb.append('.');
        }
        sb.append(tableName);
        return sb.toString();
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
        QualifiedTableName other = (QualifiedTableName) obj;
        return Objects.equals(catalogName, other.catalogName) //
                && Objects.equals(schemaName, other.schemaName) //
                && Objects.equals(tableName, other.tableName);
    }

    @Override
    public String toString() {
        return getFullName();
    }

    @Override
    public void readObject(JsonObject o)
            throws ParseException {
        catalogName = o.getString(K_CATALOG_NAME);
        schemaName = o.getString(K_SCHEMA_NAME);
        tableName = o.getString(K_TABLE_NAME);
    }

    @Override
    public void writeObject(IJsonOut out)
            throws IOException, FormatException {
        if (catalogName != null)
            out.entry(K_CATALOG_NAME, catalogName);
        if (schemaName != null)
            out.entry(K_SCHEMA_NAME, schemaName);
        if (tableName != null)
            out.entry(K_TABLE_NAME, tableName);
    }

    @Override
    public void readObject(IElement o)
            throws ParseException, LoaderException {
        catalogName = o.getAttribute(K_CATALOG_NAME);
        schemaName = o.getAttribute(K_SCHEMA_NAME);
        tableName = o.getAttribute(K_TABLE_NAME);
    }

    @Override
    public void writeObject(IXmlOutput out)
            throws XMLStreamException, FormatException {
        if (catalogName != null)
            out.attribute(K_CATALOG_NAME, catalogName);
        if (schemaName != null)
            out.attribute(K_SCHEMA_NAME, schemaName);
        if (tableName != null)
            out.attribute(K_TABLE_NAME, tableName);
    }

}
