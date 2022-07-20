package net.bodz.bas.t.table;

import java.io.Serializable;
import java.util.Objects;

public class QualifiedTableName
        implements
            Serializable {

    private static final long serialVersionUID = 1L;

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

    public void setQualifiedName(String name) {
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

    public String getQualifiedName() {
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
        return Objects.equals(catalogName, other.catalogName) && Objects.equals(schemaName, other.schemaName)
                && Objects.equals(tableName, other.tableName);
    }

    @Override
    public String toString() {
        return getQualifiedName();
    }

}
