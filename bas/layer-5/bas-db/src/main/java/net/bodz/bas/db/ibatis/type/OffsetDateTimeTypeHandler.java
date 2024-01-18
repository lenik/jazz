package net.bodz.bas.db.ibatis.type;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.temporal.TemporalAccessor;

import org.apache.ibatis.type.Alias;
import org.apache.ibatis.type.MappedTypes;

import net.bodz.bas.db.ibatis.AliasedType;

@Alias("OffsetDateTime")
@AliasedType
@MappedTypes(OffsetDateTime.class)
public class OffsetDateTimeTypeHandler
        extends AbstractTemporalTypeHandler<OffsetDateTime> {

    @Override
    protected OffsetDateTime toTemporal(Object o) {
        if (o.getClass() == OffsetDateTime.class)
            return (OffsetDateTime) o;
        if (o instanceof TemporalAccessor)
            return OffsetDateTime.from((TemporalAccessor) o);
        return null;
    }

    @Override
    protected OffsetDateTime toTemporal(Timestamp timestamp) {
        Instant instant = Instant.ofEpochMilli(timestamp.getTime());
        return OffsetDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

}
