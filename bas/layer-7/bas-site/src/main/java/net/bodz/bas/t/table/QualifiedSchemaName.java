package net.bodz.bas.t.table;

import java.io.Serializable;
import java.util.Objects;

public class QualifiedSchemaName
        implements
            Serializable {

    private static final long serialVersionUID = 1L;

    protected String catalogName;
    protected String schemaName;

    public QualifiedSchemaName() {
    }

    public QualifiedSchemaName(String catalogName, String schemaName) {
        this.catalogName = catalogName;
        this.schemaName = schemaName;
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

    public void setQualifiedName(String name) {
        int dot = name.lastIndexOf('.');
        if (dot != -1) {
            String catalog = name.substring(0, dot);
            name = name.substring(dot + 1);
            this.catalogName = catalog;
        }
        this.schemaName = name;
    }

    public String getQualifiedName() {
        StringBuilder sb = new StringBuilder();
        if (catalogName != null) {
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
        return getQualifiedName();
    }

}
