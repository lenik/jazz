package net.bodz.bas.db.sql.dialect;

import java.sql.Date;
import java.sql.Time;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZonedDateTime;

import net.bodz.bas.c.java.time.DateTimes;
import net.bodz.bas.c.type.TypeId;
import net.bodz.bas.c.type.TypeKind;
import net.bodz.bas.db.sql.DataType;

public interface ISqlDialect {

    String getStatementTerminator();

    String getNullLiteral();

    boolean isReservedKeyword(String token);

    String qName(String name);

    default String qName(String... names) {
        StringBuilder buf = new StringBuilder(names.length * 20);
        for (String name : names) {
            if (name == null)
                continue;
            if (buf.length() != 0)
                buf.append('.');
            buf.append(qName(name));
        }
        return buf.toString();
    }

    String qNameSmart(String name);

    default String qNameSmart(String... names) {
        StringBuilder buf = new StringBuilder(names.length * 20);
        for (String name : names) {
            if (name == null)
                continue;
            if (buf.length() != 0)
                buf.append('.');
            buf.append(qNameSmart(name));
        }
        return buf.toString();
    }

    default String qNames(String composite) {
        String[] names = composite.split("\\.");
        return qName(names);
    }

    default String qNamesSmart(String composite) {
        String[] names = composite.split("\\.");
        return qNameSmart(names);
    }

    String qString(String string);

    // date time

    String sqlDateFormat(String javaDateTimeFormat);

    String toDate(String dateStr, String sqlDateFormat);

    String qDate(java.util.Date date);

    String qDate(Date date);

    String qTime(Time time);

    // java.time

    String qInstant(Instant instant);

    String qZonedDateTime(ZonedDateTime zonedDateTime);

    String qOffsetDateTime(OffsetDateTime zonedDateTime);

    String qOffsetTime(OffsetTime zonedDateTime);

    String qLocalDateTime(LocalDateTime localDateTime);

    String qLocalDate(LocalDate localDate);

    String qLocalTime(LocalTime localTime);

    default String qValue(Object value) {
        if (value == null)
            return getNullLiteral();

        int typeId = TypeKind.getTypeId(value.getClass());
        switch (typeId) {
            case TypeId.BYTE:
            case TypeId.SHORT:
            case TypeId.INTEGER:
            case TypeId.LONG:
            case TypeId._byte:
            case TypeId._short:
            case TypeId._int:
            case TypeId._long:

            case TypeId.DOUBLE:
            case TypeId.FLOAT:
            case TypeId._double:
            case TypeId._float:

            case TypeId.BOOLEAN:
            case TypeId._boolean:
                return value.toString();

            case TypeId.SQL_DATE:
                return qLocalDate(DateTimes.convert((java.sql.Date) value));

            case TypeId.SQL_TIME:
                return qLocalTime(DateTimes.convert((java.sql.Time) value));

            case TypeId.TIMESTAMP:
                return qLocalDateTime(DateTimes.convert((java.sql.Timestamp) value).toLocalDateTime());

            case TypeId.DATE:
                return qLocalDateTime(DateTimes.convertDate((java.util.Date) value).toLocalDateTime());

            case TypeId.ZONED_DATE_TIME:
                return qZonedDateTime((ZonedDateTime) value);
            case TypeId.OFFSET_DATE_TIME:
                return qOffsetDateTime((OffsetDateTime) value);
            case TypeId.OFFSET_TIME:
                return qOffsetTime((OffsetTime) value);
            case TypeId.LOCAL_DATE_TIME:
                return qLocalDateTime((LocalDateTime) value);
            case TypeId.LOCAL_DATE:
                return qLocalDate((LocalDate) value);
            case TypeId.LOCAL_TIME:
                return qLocalTime((LocalTime) value);
            case TypeId.INSTANT:
                return qInstant((Instant) value);

            case TypeId.CHARACTER:
            case TypeId._char:
            case TypeId.STRING:
            default:
                return qString(value.toString());
        }
    }

    default DataType getDefaultType(int sqlType) {
        return getDefaultType(sqlType, null);
    }

    default DataType getDefaultType(String sqlTypeName) {
        return getDefaultType(0, sqlTypeName);
    }

    DataType getDefaultType(int sqlType, String sqlTypeName);

    DataType getDefaultType(Class<?> javaClass);

}
