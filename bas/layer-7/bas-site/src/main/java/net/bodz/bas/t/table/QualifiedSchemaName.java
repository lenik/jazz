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

public class QualifiedSchemaName
        implements
            IJsonForm,
            IXmlForm,
            Serializable {

    private static final long serialVersionUID = 1L;

    public static final String K_CATALOG_NAME = "catalogName";
    public static final String K_SCHEMA_NAME = "schemaName";

    protected String catalogName;
    protected String schemaName;

    public QualifiedSchemaName() {
    }

    public QualifiedSchemaName(String catalogName, String schemaName) {
        this.catalogName = catalogName;
        this.schemaName = schemaName;
    }

    public static QualifiedSchemaName parse(String fullName) {
        QualifiedSchemaName o = new QualifiedSchemaName();
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

    public void clear() {
        this.catalogName = null;
        this.schemaName = null;
    }

    public void assign(String catalogName, String schemaName) {
        this.catalogName = catalogName;
        this.schemaName = schemaName;
    }

    public String getFullName() {
        StringBuilder sb = new StringBuilder();
        if (catalogName != null) {
            sb.append(catalogName);
            sb.append('.');
        }
        sb.append(schemaName);
        return sb.toString();
    }

    public void parseFullName(String name) {
        int dot = name.lastIndexOf('.');
        if (dot != -1) {
            String catalog = name.substring(0, dot);
            name = name.substring(dot + 1);
            this.catalogName = catalog;
        }
        this.schemaName = name;
    }

    public boolean isCatalogSpecified(QualifiedSchemaName orig) {
        return isCatalogSpecified(orig, false);
    }

    public boolean isCatalogSpecified(QualifiedSchemaName orig, boolean ignoreCase) {
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

    public String getCompactName(QualifiedSchemaName orig) {
        return getCompactName(orig, false);
    }

    public String getCompactName(QualifiedSchemaName orig, boolean ignoreCase) {
        if (orig == null)
            return getFullName();
        StringBuilder sb = new StringBuilder();
        if (isCatalogSpecified(orig, ignoreCase)) {
            sb.append(catalogName);
            sb.append('.');
        }
        sb.append(schemaName);
        return sb.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(catalogName, schemaName);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        QualifiedSchemaName other = (QualifiedSchemaName) obj;
        return Objects.equals(catalogName, other.catalogName) //
                && Objects.equals(schemaName, other.schemaName);
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
    }

    @Override
    public void writeObject(IJsonOut out)
            throws IOException, FormatException {
        if (catalogName != null)
            out.entry(K_CATALOG_NAME, catalogName);
        if (schemaName != null)
            out.entry(K_SCHEMA_NAME, schemaName);
    }

    @Override
    public void readObject(IElement o)
            throws ParseException, LoaderException {
        catalogName = o.getAttribute(K_CATALOG_NAME);
        schemaName = o.getAttribute(K_SCHEMA_NAME);
    }

    @Override
    public void writeObject(IXmlOutput out)
            throws XMLStreamException, FormatException {
        if (catalogName != null)
            out.attribute(K_CATALOG_NAME, catalogName);
        if (schemaName != null)
            out.attribute(K_SCHEMA_NAME, schemaName);
    }

}
