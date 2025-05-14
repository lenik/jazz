package net.bodz.bas.db.sql.dialect;

import java.sql.Time;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZonedDateTime;
import java.util.Date;

import net.bodz.bas.t.model.AbstractDecorator;

public abstract class DecoratedSqlDialect
        extends AbstractDecorator<ISqlDialect>
        implements ISqlDialect {

    public DecoratedSqlDialect(ISqlDialect _orig) {
        super(_orig);
    }

    @Override
    public String qName(String name) {
        return getWrapped().qName(name);
    }

    @Override
    public boolean isReservedKeyword(String token) {
        return getWrapped().isReservedKeyword(token);
    }

    @Override
    public String getNullLiteral() {
        return getWrapped().getNullLiteral();
    }

    @Override
    public String getStatementTerminator() {
        return getWrapped().getStatementTerminator();
    }

    @Override
    public String qNameSmart(String name) {
        return getWrapped().qNameSmart(name);
    }

    @Override
    public String qString(String string) {
        return getWrapped().qString(string);
    }

    @Override
    public String sqlDateFormat(String javaDateTimeFormat) {
        return getWrapped().sqlDateFormat(javaDateTimeFormat);
    }

    @Override
    public String toDate(String dateStr, String sqlDateFormat) {
        return getWrapped().toDate(dateStr, sqlDateFormat);
    }

    @Override
    public String qDate(Date date) {
        return getWrapped().qDate(date);
    }

    @Override
    public String qDate(java.sql.Date date) {
        return getWrapped().qDate(date);
    }

    @Override
    public String qTime(Time time) {
        return getWrapped().qTime(time);
    }

    @Override
    public String qInstant(Instant instant) {
        return getWrapped().qInstant(instant);
    }

    @Override
    public String qZonedDateTime(ZonedDateTime zonedDateTime) {
        return getWrapped().qZonedDateTime(zonedDateTime);
    }

    @Override
    public String qOffsetDateTime(OffsetDateTime zonedDateTime) {
        return getWrapped().qOffsetDateTime(zonedDateTime);
    }

    @Override
    public String qOffsetTime(OffsetTime zonedDateTime) {
        return getWrapped().qOffsetTime(zonedDateTime);
    }

    @Override
    public String qLocalDate(LocalDate localDate) {
        return getWrapped().qLocalDate(localDate);
    }

    @Override
    public String qLocalDateTime(LocalDateTime localDateTime) {
        return getWrapped().qLocalDateTime(localDateTime);
    }

    @Override
    public String qLocalTime(LocalTime localTime) {
        return getWrapped().qLocalTime(localTime);
    }

}
