package net.bodz.lily.t.struct;

import java.io.IOException;

import org.joda.time.DateTime;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.json.JsonObject;

public interface IValidControl
        extends
            IJsonForm {

    boolean isValid();

    void setValid(boolean valid);

    DateTime getValidSince();

    void setValidSince(DateTime since);

    DateTime getValidUntil();

    void setValidUntil(DateTime until);

    @Override
    default void jsonIn(JsonObject o, JsonFormOptions opts)
            throws ParseException {
        setValid(o.getBoolean("valid", false));
        setValidSince(o.getDateTime("validSince"));
        setValidUntil(o.getDateTime("validUntil"));
    }

    @Override
    default void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException, FormatException {
        out.entry("valid", isValid());
        out.entry("validSince", getValidSince());
        out.entry("validUntil", getValidUntil());
    }

}
