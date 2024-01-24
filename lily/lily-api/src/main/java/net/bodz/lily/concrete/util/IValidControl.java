package net.bodz.lily.concrete.util;

import java.io.IOException;
import java.time.ZonedDateTime;

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

    ZonedDateTime getValidSince();

    void setValidSince(ZonedDateTime since);

    ZonedDateTime getValidUntil();

    void setValidUntil(ZonedDateTime until);

    @Override
    default void jsonIn(JsonObject o, JsonFormOptions opts)
            throws ParseException {
        setValid(o.getBoolean("valid", false));
        setValidSince(o.getZonedDateTime("validSince"));
        setValidUntil(o.getZonedDateTime("validUntil"));
    }

    @Override
    default void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException, FormatException {
        out.entry("valid", isValid());
        out.entry("validSince", getValidSince());
        out.entry("validUntil", getValidUntil());
    }

}
