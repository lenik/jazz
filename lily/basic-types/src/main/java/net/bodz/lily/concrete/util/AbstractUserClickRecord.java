package net.bodz.lily.concrete.util;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.meta.decl.NotNull;

public abstract class AbstractUserClickRecord
        extends MixinStruct {

    ZonedDateTime time;

    public ZonedDateTime getTime() {
        return time;
    }

    public void setTime(ZonedDateTime time) {
        this.time = time;
    }

    public void setTime(long time) {
        Instant instant = Instant.ofEpochMilli(time);
        this.time = ZonedDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    @Override
    public void jsonIn(@NotNull JsonObject o, JsonFormOptions opts)
            throws ParseException {
        time = o.getZonedDateTime("time", time);
    }

    @Override
    public void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException {
        out.entry("time", time);
    }

}
