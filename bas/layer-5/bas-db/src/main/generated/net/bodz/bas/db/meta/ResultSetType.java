package net.bodz.bas.db.meta;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import net.bodz.bas.db.jdbc.util.IResultColumnMetaData;
import net.bodz.bas.db.jdbc.util.MutableResultColumnMetaData;

public class ResultSetType
        implements IGenericRowType<ResultSetMetaData, ResultSet> {

    @Override
    public int getColumnCount(ResultSetMetaData resultSetMetaData)
            throws SQLException {
        return resultSetMetaData.getColumnCount();
    }

    @Override
    public IResultColumnMetaData getColumn(ResultSetMetaData resultSetMetaData, int index)
            throws SQLException {
        MutableResultColumnMetaData md = new MutableResultColumnMetaData();
        md.readObject(resultSetMetaData, index + 1);
        return md;
    }

    @Override
    public Object getColumnData(ResultSet resultSet, int index)
            throws SQLException {
        return resultSet.getObject(index + 1);
    }

    public static final ResultSetType INSTANCE = new ResultSetType();

}
