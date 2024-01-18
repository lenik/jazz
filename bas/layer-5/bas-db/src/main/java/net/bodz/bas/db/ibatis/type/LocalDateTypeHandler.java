package net.bodz.bas.db.ibatis.type;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.temporal.TemporalAccessor;

import org.apache.ibatis.type.Alias;
import org.apache.ibatis.type.MappedTypes;

import net.bodz.bas.db.ibatis.AliasedType;

@Alias("LocalDate")
@AliasedType
@MappedTypes(LocalDate.class)
public class LocalDateTypeHandler
        extends AbstractTemporalTypeHandler<LocalDate> {

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
        return LocalDate.ofInstant(instant, ZoneOffset.UTC);
    }

}
