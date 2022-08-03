package net.bodz.bas.t.catalog;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IJDBCMetaDataHandler {

    void schema(ResultSet rs)
            throws SQLException;

    void table(ResultSet rs)
            throws SQLException;

    void column(ResultSet rs)
            throws SQLException;

    void primaryKey(ITableMetadata table, TableKey primaryKey)
            throws SQLException;

    void crossReference(ITableMetadata table, CrossReference crossRef)
            throws SQLException;

}
