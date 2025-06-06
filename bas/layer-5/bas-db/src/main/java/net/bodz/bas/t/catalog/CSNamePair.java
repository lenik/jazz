package net.bodz.bas.t.catalog;

import java.io.IOException;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

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
import net.bodz.bas.meta.decl.NotNull;

abstract class CSNamePair
        implements
            IJsonForm,
            IXmlForm,
            Serializable {

    private static final long serialVersionUID = 1L;

    public static final String K_CATALOG_NAME = "catalogName";
    public static final String K_SCHEMA_NAME = "schemaName";

    protected String catalogName;
    protected String schemaName;

    public CSNamePair() {
    }

    public CSNamePair(String catalogName, String schemaName) {
        this.catalogName = catalogName;
        this.schemaName = schemaName;
    }

    public SchemaOid toSchemaId() {
        return new SchemaOid(catalogName, schemaName);
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

    protected void assign(String catalogName, String schemaName) {
        this.catalogName = catalogName;
        this.schemaName = schemaName;
    }

    public String getFullName() {
        return buildFullName().toString();
    }

    protected StringBuilder buildFullName() {
        StringBuilder sb = new StringBuilder();
        if (catalogName != null) {
            sb.append(catalogName);
        }
        if (schemaName != null) {
            if (sb.length() != 0)
                sb.append('.');
            sb.append(schemaName);
        }
        return sb;
    }

    public void setFullName(String s) {
        int dot = s.lastIndexOf('.');
        if (dot != -1) {
            catalogName = s.substring(0, dot);
            s = s.substring(dot + 1);
        }
        schemaName = s;
    }

    public boolean isCatalogNameNeeded(boolean ignoreCase, CSNamePair... searchNames) {
        if (catalogName == null)
            return false;
        for (CSNamePair np : searchNames)
            if (np.catalogName != null)
                if (ignoreCase) {
                    if (catalogName.equalsIgnoreCase(np.catalogName))
                        return false;
                } else {
                    if (catalogName.equals(np.catalogName))
                        return false;
                }
        return true;
    }

    public boolean isCatalogNameNeeded(boolean ignoreCase, String... searchCatalogNames) {
        if (catalogName == null)
            return false;
        if (searchCatalogNames != null)
            for (String n : searchCatalogNames)
                if (ignoreCase) {
                    if (catalogName.equalsIgnoreCase(n))
                        return false;
                } else {
                    if (catalogName.equals(n))
                        return false;
                }
        return true;
    }

    public boolean isSchemaSpecified(boolean ignoreCase, CSNamePair... searchNames) {
        if (schemaName == null)
            return false;
        for (CSNamePair np : searchNames)
            if (np.schemaName != null)
                if (ignoreCase) {
                    if (schemaName.equalsIgnoreCase(np.schemaName))
                        return false;
                } else {
                    if (schemaName.equals(np.schemaName))
                        return false;
                }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder fullName = buildFullName();
        return fullName.toString();
    }

    @Override
    public void jsonIn(@NotNull JsonObject o, JsonFormOptions opts)
            throws ParseException {
        catalogName = o.getString(K_CATALOG_NAME);
        schemaName = o.getString(K_SCHEMA_NAME);
    }

    @Override
    public void jsonOut(IJsonOut out, JsonFormOptions opts)
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

    public void readFromJDBC(ResultSet rs)
            throws SQLException {
        catalogName = rs.getString("TABLE_CAT");
        schemaName = rs.getString("TABLE_SCHEM");
    }

}
