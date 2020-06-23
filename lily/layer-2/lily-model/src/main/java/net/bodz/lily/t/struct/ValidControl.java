package net.bodz.lily.t.struct;

import java.io.IOException;

import org.joda.time.DateTime;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonObject;

public class ValidControl
        extends MixinStruct {

    boolean valid;
    DateTime since;
    DateTime until;

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public DateTime getSince() {
        return since;
    }

    public void setSince(DateTime since) {
        this.since = since;
    }

    public DateTime getUntil() {
        return until;
    }

    public void setUntil(DateTime until) {
        this.until = until;
    }

    @Override
    public void readObject(JsonObject o)
            throws ParseException {
        valid = o.getBoolean("valid", valid);
        since = o.getDateTime("since", since);
        until = o.getDateTime("until", until);

    }

    @Override
    public void writeObject(IJsonOut out)
            throws IOException {
        out.entry("valid", valid);
        out.entry("since", since);
        out.entry("until", until);
    }

}
