package net.bodz.bas.t.catalog;

import net.bodz.bas.err.DuplicatedKeyException;

public interface ISchemaDirectory {

    /**
     * Check if the given name is valid with-in this directory.
     *
     * @return <code>false</code> if the name is outside of this directory.
     */
    boolean isValidSchemaId(SchemaOid id);

    default void checkSchemaId(SchemaOid id) {
        if (!isValidSchemaId(id))
            throw new IllegalArgumentException("Invalid schema id: " + id);
    }

    default SchemaList findSchemas(SchemaOid pattern) {
        return findSchemas(pattern, false);
    }

    SchemaList findSchemas(SchemaOid pattern, boolean ignoreCase);

    default ISchemaMetadata getSchema(SchemaOid pattern) {
        return getSchema(pattern, false);
    }

    default ISchemaMetadata getSchema(SchemaOid pattern, boolean ignoreCase) {
        SchemaList schemaList = findSchemas(pattern, ignoreCase);
        if (schemaList.size() > 1)
            throw new DuplicatedKeyException("More than single schema matched: " + pattern);
        if (schemaList.isEmpty())
            return null;
        else
            return schemaList.get(0);
    }

}
