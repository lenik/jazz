package net.bodz.bas.db.ibatis.type;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;

import org.apache.ibatis.type.Alias;
import org.apache.ibatis.type.MappedTypes;

import net.bodz.bas.db.ibatis.AliasedType;

@Alias("LocalDate")
@AliasedType
@MappedTypes(LocalDate.class)
public class LocalDateTypeHandler
        extends AbstractTemporalTypeHandler<LocalDate> {

    public LocalDateTypeHandler() {
        super(LocalDate.class);
    }

    @Override
    protected LocalDateTime toLocalDateTime(LocalDate val) {
        return LocalDateTime.of( //
                val.get(ChronoField.YEAR), //
                val.get(ChronoField.MONTH_OF_YEAR), //
                val.get(ChronoField.DAY_OF_MONTH), //
                0, 0);
    }

    @Override
    protected LocalDate toLocalDate(LocalDate val) {
        return val;
    }

    @Override
    protected LocalTime toLocalTime(LocalDate val) {
        return LocalTime.of(0, 0);
    }

    @Override
    protected LocalDate toTemporal(Object o) {
        if (o.getClass() == LocalDate.class)
            return (LocalDate) o;
        if (o instanceof TemporalAccessor)
            return LocalDate.from((TemporalAccessor) o);
        return null;
    }

    @Override
    protected LocalDate toTemporal(Timestamp timestamp) {
        Instant instant = Instant.ofEpochMilli(timestamp.getTime());
        // return LocalDate.ofInstant(instant, ZoneOffset.UTC);
        return instant.atOffset(ZoneOffset.UTC).toLocalDate();
    }

}
