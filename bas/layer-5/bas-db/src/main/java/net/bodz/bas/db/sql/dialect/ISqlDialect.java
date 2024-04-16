package net.bodz.bas.db.sql.dialect;

import java.sql.Date;
import java.sql.Time;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;

public interface ISqlDialect {

    String getStatementTerminator();

    String getNullLiteral();

    boolean isReservedKeyword(String token);

    String qName(String name);

    String qNameSmart(String name);

    String qNames(String composite);

    String qNamesSmart(String composite);

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

    String qLocalDateTime(LocalDateTime localDateTime);

    String qLocalDate(LocalDate localDate);

    String qLocalTime(LocalTime localTime);

}
