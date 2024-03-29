package net.bodz.bas.t.catalog;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IJDBCMetaDataHandler {

    default ISchemaMetadata schema(ResultSet rs)
            throws SQLException {
        return null;
    }

    default void endSchema(ISchemaMetadata schema) {
    }

    default ITableMetadata table(ResultSet rs)
            throws SQLException {
        return null;
    }

    default void endTable(ITableMetadata table) {
    }

    default IColumnMetadata column(ResultSet rs)
            throws SQLException {
        return null;
    }

    default void primaryKey(ITableMetadata table, TableKey primaryKey)
            throws SQLException {
    }

    default void crossReference(ITableMetadata table, CrossReference crossRef)
            throws SQLException {
    }

    default void viewColumnUsage(ResultSet rs)
            throws SQLException {
    }

}
