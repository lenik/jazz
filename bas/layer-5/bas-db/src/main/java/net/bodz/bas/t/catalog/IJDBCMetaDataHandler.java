package net.bodz.bas.t.catalog;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IJDBCMetaDataHandler {

    default void schema(ResultSet rs)
            throws SQLException {
    }

    default void table(ResultSet rs)
            throws SQLException {
    }

    default void column(ResultSet rs)
            throws SQLException {
    }

    default void primaryKey(ITableMetadata table, TableKey primaryKey)
            throws SQLException {
    }

    default void crossReference(ITableMetadata table, CrossReference crossRef)
            throws SQLException {
    }

}
