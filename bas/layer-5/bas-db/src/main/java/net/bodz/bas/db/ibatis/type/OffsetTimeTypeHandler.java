package net.bodz.bas.db.ibatis.type;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetTime;
import java.time.ZoneId;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;

import org.apache.ibatis.type.Alias;
import org.apache.ibatis.type.MappedTypes;

import net.bodz.bas.db.ibatis.AliasedType;

@Alias("OffsetTime")
@AliasedType
@MappedTypes(OffsetTime.class)
public class OffsetTimeTypeHandler
        extends AbstractTemporalTypeHandler<OffsetTime> {

    public OffsetTimeTypeHandler() {
        super(OffsetTime.class);
    }

    @Override
    protected LocalDateTime toLocalDateTime(OffsetTime val) {
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
    protected LocalDate toLocalDate(OffsetTime val) {
        return LocalDate.of( //
                DEFAULT_YEAR, //
                DEFAULT_MONTH, //
                DEFAULT_DAY);
    }

    @Override
    protected LocalTime toLocalTime(OffsetTime val) {
        return LocalTime.of(//
                val.get(ChronoField.HOUR_OF_DAY), //
                val.get(ChronoField.MINUTE_OF_HOUR), //
                val.get(ChronoField.SECOND_OF_MINUTE), //
                val.get(ChronoField.NANO_OF_SECOND));
    }

    @Override
    protected OffsetTime toTemporal(Object o) {
        if (o.getClass() == OffsetTime.class)
            return (OffsetTime) o;
        if (o instanceof TemporalAccessor)
            return OffsetTime.from((TemporalAccessor) o);
        return null;
    }

    @Override
    protected OffsetTime toTemporal(Timestamp timestamp) {
        Instant instant = Instant.ofEpochMilli(timestamp.getTime());
        return OffsetTime.ofInstant(instant, ZoneId.systemDefault());
    }

}
