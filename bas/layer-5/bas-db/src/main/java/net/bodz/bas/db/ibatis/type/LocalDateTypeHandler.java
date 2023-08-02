package net.bodz.bas.db.ibatis.type;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalTime;

import org.apache.ibatis.type.Alias;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import net.bodz.bas.db.ibatis.AliasedType;
import net.bodz.bas.db.ibatis.TypeHandler;

@Alias("JodaLocalDate")
@AliasedType
@MappedTypes(LocalDate.class)
public class LocalDateTypeHandler
        extends TypeHandler<LocalDate> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, LocalDate parameter, JdbcType jdbcType)
            throws SQLException {
        if (jdbcType == null)
            jdbcType = JdbcType.DATE;
        switch (jdbcType) {
        case DATE:
            java.time.LocalDate localDate = java.time.LocalDate.of(//
                    parameter.getYear(), //
                    parameter.getMonthOfYear(), //
                    parameter.getDayOfMonth());
            ps.setObject(i, localDate);
            break;

        case TIME:
            LocalTime localTime = LocalTime.MIN;
            ps.setObject(i, localTime);
            break;

        case TIMESTAMP:
            DateTime pad = parameter.toDateTimeAtStartOfDay();
            Timestamp timestamp = new Timestamp(pad.getMillis());
            ps.setTimestamp(i, timestamp);
            break;

        default:
            throw new SQLException("Invalid jdbcType to set with datetime: " + jdbcType);
        }
    }

    @Override
    public LocalDate getNullableResult(ResultSet rs, String columnName)
            throws SQLException {
        java.time.LocalDate localDate = rs.getObject(columnName, java.time.LocalDate.class);
        if (localDate == null)
            return null;
        LocalDate date = new LocalDate(//
                localDate.getYear(), //
                localDate.getMonthValue(), //
                localDate.getDayOfMonth());
        return date;
    }

    @Override
    public LocalDate getNullableResult(ResultSet rs, int columnIndex)
            throws SQLException {
        java.time.LocalDate localDate = rs.getObject(columnIndex, java.time.LocalDate.class);
        if (localDate == null)
            return null;
        LocalDate date = new LocalDate(//
                localDate.getYear(), //
                localDate.getMonthValue(), //
                localDate.getDayOfMonth());
        return date;
    }

    @Override
    public LocalDate getNullableResult(CallableStatement cs, int columnIndex)
            throws SQLException {
        java.time.LocalDate localDate = cs.getObject(columnIndex, java.time.LocalDate.class);
        if (localDate == null)
            return null;
        LocalDate date = new LocalDate(//
                localDate.getYear(), //
                localDate.getMonthValue(), //
                localDate.getDayOfMonth());
        return date;
    }

}
