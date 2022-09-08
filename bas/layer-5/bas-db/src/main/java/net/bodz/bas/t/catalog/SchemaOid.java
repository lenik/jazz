package net.bodz.bas.t.catalog;

import java.util.Objects;

public class SchemaOid
        extends CSNamePair {

    private static final long serialVersionUID = 1L;

    public SchemaOid() {
    }

    public SchemaOid(String catalogName, String schemaName) {
        super(catalogName, schemaName);
    }

    public static SchemaOid parse(String fullName) {
        SchemaOid o = new SchemaOid();
        o.setFullName(fullName);
        return o;
    }

    @Override
    public void assign(String catalogName, String schemaName) {
        super.assign(catalogName, schemaName);
    }

    public String getCompactName(String contextCatalogName) {
        return getCompactName(contextCatalogName, false);
    }

    public String getCompactName(String contextCatalogName, boolean ignoreCase) {
        if (contextCatalogName == null)
            return getFullName();
        StringBuilder sb = new StringBuilder();
        if (isCatalogDifferent(contextCatalogName, ignoreCase)) {
            sb.append(catalogName);
            sb.append('.');
        }
        sb.append(schemaName);
        return sb.toString();
    }

    public boolean contains(SchemaOid o, boolean ignoreCase) {
        if (ignoreCase)
            return containsIgnoreCase(o);
        else
            return contains(o);
    }

    public boolean contains(SchemaOid o) {
        if (!NamePattern.matches(catalogName, o.catalogName))
            return false;
        if (!NamePattern.matches(schemaName, o.schemaName))
            return false;
        return true;
    }

    public boolean containsIgnoreCase(SchemaOid o) {
        if (!NamePattern.matchesIgnoreCase(catalogName, o.catalogName))
            return false;
        if (!NamePattern.matchesIgnoreCase(schemaName, o.schemaName))
            return false;
        return true;
    }

    public boolean contains(TableOid o, boolean ignoreCase) {
        if (ignoreCase)
            return containsIgnoreCase(o);
        else
            return contains(o);
    }

    public boolean contains(TableOid o) {
        if (!NamePattern.matches(catalogName, o.catalogName))
            return false;
        if (!NamePattern.matches(schemaName, o.schemaName))
            return false;
        return true;
    }

    public boolean containsIgnoreCase(TableOid o) {
        if (!NamePattern.matchesIgnoreCase(catalogName, o.catalogName))
            return false;
        if (!NamePattern.matchesIgnoreCase(schemaName, o.schemaName))
            return false;
        return true;
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
        SchemaOid other = (SchemaOid) obj;
        return Objects.equals(catalogName, other.catalogName) //
                && Objects.equals(schemaName, other.schemaName);
    }

}
