package net.bodz.bas.db.ibatis.type;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;

import org.apache.ibatis.type.JdbcType;

import net.bodz.bas.db.ibatis.TypeHandler;

public abstract class AbstractTemporalTypeHandler<T extends TemporalAccessor>
        extends TypeHandler<T> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int paramIndex, T parameter, JdbcType jdbcType)
            throws SQLException {
        if (jdbcType == null)
            jdbcType = JdbcType.TIMESTAMP;
        switch (jdbcType) {
        case DATE:
            LocalDate localDate = parameter.getClass() == LocalDate.class ? (LocalDate) parameter
                    : LocalDate.of( //
                            parameter.get(ChronoField.YEAR), //
                            parameter.get(ChronoField.MONTH_OF_YEAR), //
                            parameter.get(ChronoField.DAY_OF_MONTH));
            ps.setObject(paramIndex, localDate);
            break;

        case TIME:
            LocalTime localTime = parameter.getClass() == LocalTime.class ? (LocalTime) parameter
                    : LocalTime.of(//
                            parameter.get(ChronoField.HOUR_OF_DAY), //
                            parameter.get(ChronoField.MINUTE_OF_HOUR), //
                            parameter.get(ChronoField.SECOND_OF_MINUTE), //
                            parameter.get(ChronoField.NANO_OF_SECOND));
            ps.setObject(paramIndex, localTime);
            break;

        case TIMESTAMP:
            LocalDateTime localDateTime = parameter.getClass() == LocalDateTime.class ? (LocalDateTime) parameter
                    : LocalDateTime.of(//
                            parameter.get(ChronoField.YEAR), //
                            parameter.get(ChronoField.MONTH_OF_YEAR), //
                            parameter.get(ChronoField.DAY_OF_MONTH), //
                            parameter.get(ChronoField.HOUR_OF_DAY), //
                            parameter.get(ChronoField.MINUTE_OF_HOUR), //
                            parameter.get(ChronoField.SECOND_OF_MINUTE), //
                            parameter.get(ChronoField.NANO_OF_SECOND));
            ps.setObject(paramIndex, localDateTime);
            break;

        case OTHER:
        case JAVA_OBJECT:

        default:
            throw new SQLException("Invalid jdbcType to set with datetime: " + jdbcType);
        }
    }

    protected abstract T toTemporal(Object o);

    protected abstract T toTemporal(Timestamp timestamp);

    @Override
    public T getNullableResult(ResultSet rs, String columnName)
            throws SQLException {
        Object obj = rs.getObject(columnName);
        if (obj == null)
            return null;

        T temporal = toTemporal(obj);
        if (temporal != null)
            return temporal;

        Timestamp timestamp = rs.getTimestamp(columnName);
        return toTemporal(timestamp);
    }

    @Override
    public T getNullableResult(ResultSet rs, int columnIndex)
            throws SQLException {
        Object obj = rs.getObject(columnIndex);
        if (obj == null)
            return null;

        T temporal = toTemporal(obj);
        if (temporal != null)
            return temporal;

        Timestamp timestamp = rs.getTimestamp(columnIndex);
        return toTemporal(timestamp);
    }

    @Override
    public T getNullableResult(CallableStatement cs, int columnIndex)
            throws SQLException {
        Object obj = cs.getObject(columnIndex);
        if (obj == null)
            return null;

        T temporal = toTemporal(obj);
        if (temporal != null)
            return temporal;

        Timestamp timestamp = cs.getTimestamp(columnIndex);
        return toTemporal(timestamp);
    }

}
