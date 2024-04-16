package net.bodz.bas.db.ibatis.type;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.TemporalAccessor;

import org.apache.ibatis.type.Alias;
import org.apache.ibatis.type.MappedTypes;

import net.bodz.bas.db.ibatis.AliasedType;

@Alias("Instant")
@AliasedType
@MappedTypes(Instant.class)
public class InstantTypeHandler
        extends AbstractTemporalTypeHandler<Instant> {

    public InstantTypeHandler() {
        super(Instant.class);
    }

    @Override
    protected Instant toTemporal(Object o) {
        if (o.getClass() == Instant.class)
            return (Instant) o;
        if (o instanceof TemporalAccessor)
            return Instant.from((TemporalAccessor) o);
        return null;
    }

    @Override
    protected Instant toTemporal(Timestamp timestamp) {
        Instant instant = Instant.ofEpochMilli(timestamp.getTime());
        return instant;
    }

}
