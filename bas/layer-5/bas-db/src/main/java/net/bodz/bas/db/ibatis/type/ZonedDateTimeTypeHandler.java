package net.bodz.bas.db.ibatis.type;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAccessor;

import org.apache.ibatis.type.Alias;
import org.apache.ibatis.type.MappedTypes;

import net.bodz.bas.db.ibatis.AliasedType;

@Alias("ZonedDateTime")
@AliasedType
@MappedTypes(ZonedDateTime.class)
public class ZonedDateTimeTypeHandler
        extends AbstractTemporalTypeHandler<ZonedDateTime> {

    public ZonedDateTimeTypeHandler() {
        super(ZonedDateTime.class);
    }

    @Override
    protected ZonedDateTime toTemporal(Object o) {
        if (o.getClass() == ZonedDateTime.class)
            return (ZonedDateTime) o;
        if (o instanceof TemporalAccessor)
            return ZonedDateTime.from((TemporalAccessor) o);
        return null;
    }

    @Override
    protected ZonedDateTime toTemporal(Timestamp timestamp) {
        Instant instant = Instant.ofEpochMilli(timestamp.getTime());
        return ZonedDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

}
