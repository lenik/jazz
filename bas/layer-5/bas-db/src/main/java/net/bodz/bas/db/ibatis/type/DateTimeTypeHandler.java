package net.bodz.bas.db.ibatis.type;

import java.sql.*;

import org.apache.ibatis.type.JdbcType;
import org.joda.time.DateTime;

import net.bodz.bas.db.ibatis.AliasedType;
import net.bodz.bas.db.ibatis.TypeHandler;

@AliasedType
public class DateTimeTypeHandler
        extends TypeHandler<DateTime> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, DateTime parameter, JdbcType jdbcType)
            throws SQLException {
        switch (jdbcType) {
        case DATE:
            Date date = new java.sql.Date(parameter.getMillis());
            ps.setDate(i, date);
            break;

        case TIME:
            Time time = new Time(parameter.getMillis());
            ps.setTime(i, time);
            break;

        case TIMESTAMP:
            Timestamp timestamp = new Timestamp(parameter.getMillis());
            ps.setTimestamp(i, timestamp);

        default:
            throw new SQLException("Invalid jdbcType to set with datetime: " + jdbcType);
        }
    }

    @Override
    public DateTime getNullableResult(ResultSet rs, String columnName)
            throws SQLException {
        Date date = rs.getDate(columnName);
        if (date == null)
            return null;
        else
            return new DateTime(date);
    }

    @Override
    public DateTime getNullableResult(ResultSet rs, int columnIndex)
            throws SQLException {
        Date date = rs.getDate(columnIndex);
        if (date == null)
            return null;
        else
            return new DateTime(date);
    }

    @Override
    public DateTime getNullableResult(CallableStatement cs, int columnIndex)
            throws SQLException {
        Date date = cs.getDate(columnIndex);
        if (date == null)
            return null;
        else
            return new DateTime(date);
    }

}
