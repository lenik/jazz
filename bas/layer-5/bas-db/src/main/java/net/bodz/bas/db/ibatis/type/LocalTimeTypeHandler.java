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

@Alias("LocalTime")
@AliasedType
@MappedTypes(LocalTime.class)
public class LocalTimeTypeHandler
        extends AbstractTemporalTypeHandler<LocalTime> {

    public LocalTimeTypeHandler() {
        super(LocalTime.class);
    }

    @Override
    protected LocalDateTime toLocalDateTime(LocalTime val) {
        return LocalDateTime.of( //
                DEFAULT_YEAR, //
                DEFAULT_MONTH, //
                DEFAULT_DAY, //
                val.get(ChronoField.HOUR_OF_DAY), //
                val.get(ChronoField.MINUTE_OF_HOUR), //
                val.get(ChronoField.SECOND_OF_MINUTE), //
                val.get(ChronoField.NANO_OF_SECOND));
    }

    @Override
    protected LocalDate toLocalDate(LocalTime val) {
        return LocalDate.of(//
                DEFAULT_YEAR, //
                DEFAULT_MONTH, //
                DEFAULT_DAY);
    }

    @Override
    protected LocalTime toLocalTime(LocalTime val) {
        return val;
    }

    @Override
    protected LocalTime toTemporal(Object o) {
        if (o.getClass() == LocalTime.class)
            return (LocalTime) o;
        if (o instanceof TemporalAccessor)
            return LocalTime.from((TemporalAccessor) o);
        return null;
    }

    @Override
    protected LocalTime toTemporal(Timestamp timestamp) {
        Instant instant = Instant.ofEpochMilli(timestamp.getTime());
        // return LocalTime.ofInstant(instant, ZoneOffset.UTC);
        return instant.atOffset(ZoneOffset.UTC).toLocalTime();
    }

}
