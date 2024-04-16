package net.bodz.bas.db.ibatis.type;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
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

    static final int DEFAULT_YEAR = 2000;
    static final int DEFAULT_MONTH = 1;
    static final int DEFAULT_DAY = 1;

    final Class<T> type;

    public AbstractTemporalTypeHandler(Class<T> type) {
        this.type = type;
    }

    protected LocalDateTime toLocalDateTime(T val) {
        return LocalDateTime.of( //
                val.get(ChronoField.YEAR), //
                val.get(ChronoField.MONTH_OF_YEAR), //
                val.get(ChronoField.DAY_OF_MONTH), //
                val.get(ChronoField.HOUR_OF_DAY), //
                val.get(ChronoField.MINUTE_OF_HOUR), //
                val.get(ChronoField.SECOND_OF_MINUTE), //
                val.get(ChronoField.NANO_OF_SECOND));
    }

    protected LocalDate toLocalDate(T val) {
        return LocalDate.of( //
                val.get(ChronoField.YEAR), //
                val.get(ChronoField.MONTH_OF_YEAR), //
                val.get(ChronoField.DAY_OF_MONTH));
    }

    protected LocalTime toLocalTime(T val) {
        return LocalTime.of(//
                val.get(ChronoField.HOUR_OF_DAY), //
                val.get(ChronoField.MINUTE_OF_HOUR), //
                val.get(ChronoField.SECOND_OF_MINUTE), //
                val.get(ChronoField.NANO_OF_SECOND));
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int paramIndex, T parameter, JdbcType jdbcType)
            throws SQLException {
        if (jdbcType == null)
            jdbcType = JdbcType.TIMESTAMP;
        switch (jdbcType) {
        case DATE:
            LocalDate localDate = parameter.getClass() == LocalDate.class ? (LocalDate) parameter
                    : toLocalDate(parameter);
            ps.setObject(paramIndex, localDate);
            break;

        case TIME:
            LocalTime localTime = parameter.getClass() == LocalTime.class ? (LocalTime) parameter
                    : toLocalTime(parameter);
            ps.setObject(paramIndex, localTime);
            break;

        case TIMESTAMP:
            LocalDateTime localDateTime = parameter.getClass() == LocalDateTime.class ? (LocalDateTime) parameter
                    : toLocalDateTime(parameter);
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
        try {
            return rs.getObject(columnName, type);
        } catch (SQLFeatureNotSupportedException e) {
            Object obj = rs.getObject(columnName);
            if (obj == null)
                return null;

            T temporal = toTemporal(obj);
            if (temporal != null)
                return temporal;

            T val = rs.getObject(columnName, type);
            return toTemporal(val);
        }
    }

    @Override
    public T getNullableResult(ResultSet rs, int columnIndex)
            throws SQLException {
        try {
            return rs.getObject(columnIndex, type);
        } catch (SQLFeatureNotSupportedException e) {
            Object obj = rs.getObject(columnIndex);
            if (obj == null)
                return null;

            T temporal = toTemporal(obj);
            if (temporal != null)
                return temporal;

            Timestamp timestamp = rs.getTimestamp(columnIndex);
            return toTemporal(timestamp);
        }
    }

    @Override
    public T getNullableResult(CallableStatement cs, int columnIndex)
            throws SQLException {
        try {
            return cs.getObject(columnIndex, type);
        } catch (SQLFeatureNotSupportedException e) {
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

}
