package net.bodz.bas.db.ibatis.type;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.TemporalAccessor;

import org.apache.ibatis.type.Alias;
import org.apache.ibatis.type.MappedTypes;

import net.bodz.bas.db.ibatis.AliasedType;

@Alias("LocalDateTime")
@AliasedType
@MappedTypes(LocalDateTime.class)
public class LocalDateTimeTypeHandler
        extends AbstractTemporalTypeHandler<LocalDateTime> {

    public LocalDateTimeTypeHandler() {
        super(LocalDateTime.class);
    }

    @Override
    protected LocalDateTime toTemporal(Object o) {
        if (o.getClass() == LocalDateTime.class)
            return (LocalDateTime) o;
        if (o instanceof TemporalAccessor)
            return LocalDateTime.from((TemporalAccessor) o);
        return null;
    }

    @Override
    protected LocalDateTime toTemporal(Timestamp timestamp) {
        Instant instant = Instant.ofEpochMilli(timestamp.getTime());
        return LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
    }

}
