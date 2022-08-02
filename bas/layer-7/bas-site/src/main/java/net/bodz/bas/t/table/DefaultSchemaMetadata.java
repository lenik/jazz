package net.bodz.bas.t.table;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.xml.xq.IElement;
import net.bodz.bas.json.JsonObject;

public class DefaultSchemaMetadata
        extends DefaultTableMapMetadata
        implements
            ISchemaMetadata {

    private static final long serialVersionUID = 1L;

    String defaultCatalog;

    String label;
    String description;

    public DefaultSchemaMetadata() {
        super();
    }

    public DefaultSchemaMetadata(String catalogName, String schemaName) {
        super(catalogName, schemaName);
    }

    public DefaultSchemaMetadata(String qualifiedName) {
        setQualifiedName(qualifiedName);
    }

    public static DefaultSchemaMetadata fromMetaData(Connection connection, String catalogName, String schemaName)
            throws SQLException {
        DefaultSchemaMetadata schema = new DefaultSchemaMetadata(catalogName, schemaName);
        schema.readObject(connection);
        return schema;
    }

    public static DefaultSchemaMetadata fromMetaData(Connection connection, String qualifiedName)
            throws SQLException {
        DefaultSchemaMetadata schema = new DefaultSchemaMetadata(qualifiedName);
        schema.readObject(connection);
        return schema;
    }

    public String getDefaultCatalog() {
        return defaultCatalog;
    }

    public void setDefaultCatalog(String defaultCatalog) {
        this.defaultCatalog = defaultCatalog;
    }

    public boolean isCatalogSpecified() {
        if (catalogName == null)
            return false;
        if (defaultCatalog != null)
            if (catalogName.equals(defaultCatalog))
                return false;
        return true;
    }

    @Override
    public String getName() {
        return schemaName;
    }

    public void setName(String name) {
        this.schemaName = name;
    }

    @Override
    public String getNecessaryQualifiedName() {
        StringBuilder sb = new StringBuilder();
        if (isCatalogSpecified()) {
            sb.append(catalogName);
            sb.append('.');
        }
        sb.append(schemaName);
        return sb.toString();
    }

    @Override
    public void readObject(JsonObject o)
            throws ParseException {
        super.readObject(o);
        schemaName = o.getString(K_NAME);
    }

    @Override
    public void readObject(IElement x_table)
            throws ParseException, LoaderException {
        super.readObject(x_table);
        schemaName = x_table.getAttribute("name");
    }

    public void readObject(Connection connection)
            throws SQLException {
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet rs;

        // Parse from schema's metadata
        rs = metaData.getSchemas(catalogName, schemaName);
        while (rs.next()) {
            catalogName = rs.getString("schema_cat");
            schemaName = rs.getString("schema_name");
            break;
        }
        rs.close();

        rs = metaData.getTables(catalogName, schemaName, null, null);
        List<String> tableNames = new ArrayList<>();
        while (rs.next()) {
            String name = rs.getString("table_name");
            tableNames.add(name);
        }
        rs.close();

        for (String name : tableNames) {
            DefaultTableMetadata table = new DefaultTableMetadata();
            table.setCatalogName(catalogName);
            table.setSchemaName(schemaName);
            table.setName(name);
            table.readObject(connection);
            addTable(table);
        }
        rs.close();
    }

    @Override
    public String toString() {
        return schemaName + "(" + getTableNames() + ")";
    }

}
