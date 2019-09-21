package net.bodz.bas.db.ibatis.type;

import java.sql.*;

import org.apache.ibatis.type.Alias;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.joda.time.DateTime;

import net.bodz.bas.db.ibatis.AliasedType;
import net.bodz.bas.db.ibatis.TypeHandler;

@Alias("DateTime")
@AliasedType
@MappedTypes(DateTime.class)
public class DateTimeTypeHandler
        extends TypeHandler<DateTime> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, DateTime parameter, JdbcType jdbcType)
            throws SQLException {
        if (jdbcType == null)
            jdbcType = JdbcType.TIMESTAMP;
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
            break;

        default:
            throw new SQLException("Invalid jdbcType to set with datetime: " + jdbcType);
        }
    }

    @Override
    public DateTime getNullableResult(ResultSet rs, String columnName)
            throws SQLException {
        Timestamp timestamp = rs.getTimestamp(columnName);
        if (timestamp == null)
            return null;
        else
            return new DateTime(timestamp);
    }

    @Override
    public DateTime getNullableResult(ResultSet rs, int columnIndex)
            throws SQLException {
        Timestamp timestamp = rs.getTimestamp(columnIndex);
        if (timestamp == null)
            return null;
        else
            return new DateTime(timestamp);
    }

    @Override
    public DateTime getNullableResult(CallableStatement cs, int columnIndex)
            throws SQLException {
        Timestamp timestamp = cs.getTimestamp(columnIndex);
        if (timestamp == null)
            return null;
        else
            return new DateTime(timestamp);
    }

}
