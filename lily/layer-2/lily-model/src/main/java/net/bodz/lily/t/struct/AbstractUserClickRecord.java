package net.bodz.lily.t.struct;

import java.io.IOException;

import org.joda.time.DateTime;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonOut;
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
    public void readObject(JsonObject o)
            throws ParseException {
        time = o.getDateTime("time", time);
    }

    @Override
    public void writeObject(IJsonOut out)
            throws IOException {
        out.entry("time", time);
    }

}
