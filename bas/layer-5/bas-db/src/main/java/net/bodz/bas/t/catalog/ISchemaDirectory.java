package net.bodz.bas.t.catalog;

import net.bodz.bas.err.DuplicatedKeyException;

public interface ISchemaDirectory {

    /**
     * Check if the given name is valid with-in this directory.
     *
     * @return <code>false</code> if the name is outside of this directory.
     */
    boolean isValidName(QualifiedSchemaName schemaName);

    default void checkValidName(QualifiedSchemaName schemaName) {
        if (!isValidName(schemaName))
            throw new IllegalArgumentException("Invalid name: " + schemaName);
    }

    default SchemaList findSchemas(QualifiedSchemaName pattern) {
        return findSchemas(pattern, false);
    }

    SchemaList findSchemas(QualifiedSchemaName pattern, boolean ignoreCase);

    default ISchemaMetadata getSchema(QualifiedSchemaName pattern) {
        return getSchema(pattern, false);
    }

    default ISchemaMetadata getSchema(QualifiedSchemaName pattern, boolean ignoreCase) {
        SchemaList schemaList = findSchemas(pattern, ignoreCase);
        if (schemaList.size() > 1)
            throw new DuplicatedKeyException("More than single schema matched: " + pattern);
        if (schemaList.isEmpty())
            return null;
        else
            return schemaList.get(0);
    }

}
