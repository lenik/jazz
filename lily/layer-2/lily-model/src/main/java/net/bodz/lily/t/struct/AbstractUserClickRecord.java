package net.bodz.lily.t.struct;

import java.io.IOException;

import org.joda.time.DateTime;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.json.JsonObject;

public abstract class AbstractUserClickRecord
        extends MixinStruct {

    DateTime time;

    public DateTime getTime() {
        return time;
    }

    public void setTime(DateTime time) {
        this.time = time;
    }

    public void setTime(long time) {
        this.time = new DateTime(time);
    }

    @Override
    public void jsonIn(JsonObject o, JsonFormOptions opts)
            throws ParseException {
        time = o.getDateTime("time", time);
    }

    @Override
    public void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException {
        out.entry("time", time);
    }

}
