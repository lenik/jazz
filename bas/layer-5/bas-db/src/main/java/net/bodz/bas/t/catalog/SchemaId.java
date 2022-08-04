package net.bodz.bas.t.catalog;

import java.util.Objects;

public class SchemaId
        extends CSNamePair {

    private static final long serialVersionUID = 1L;

    public SchemaId() {
    }

    public SchemaId(String catalogName, String schemaName) {
        super(catalogName, schemaName);
    }

    public static SchemaId parse(String fullName) {
        SchemaId o = new SchemaId();
        o.setFullName(fullName);
        return o;
    }

    @Override
    public void assign(String catalogName, String schemaName) {
        super.assign(catalogName, schemaName);
    }

    public String getCompactName(SchemaId orig) {
        return getCompactName(orig, false);
    }

    public String getCompactName(SchemaId orig, boolean ignoreCase) {
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

    public boolean contains(SchemaId o, boolean ignoreCase) {
        if (ignoreCase)
            return containsIgnoreCase(o);
        else
            return contains(o);
    }

    public boolean contains(SchemaId o) {
        if (!NamePattern.matches(catalogName, o.catalogName))
            return false;
        if (!NamePattern.matches(schemaName, o.schemaName))
            return false;
        return true;
    }

    public boolean containsIgnoreCase(SchemaId o) {
        if (!NamePattern.matchesIgnoreCase(catalogName, o.catalogName))
            return false;
        if (!NamePattern.matchesIgnoreCase(schemaName, o.schemaName))
            return false;
        return true;
    }

    public boolean contains(TableId o, boolean ignoreCase) {
        if (ignoreCase)
            return containsIgnoreCase(o);
        else
            return contains(o);
    }

    public boolean contains(TableId o) {
        if (!NamePattern.matches(catalogName, o.catalogName))
            return false;
        if (!NamePattern.matches(schemaName, o.schemaName))
            return false;
        return true;
    }

    public boolean containsIgnoreCase(TableId o) {
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
        SchemaId other = (SchemaId) obj;
        return Objects.equals(catalogName, other.catalogName) //
                && Objects.equals(schemaName, other.schemaName);
    }

}
