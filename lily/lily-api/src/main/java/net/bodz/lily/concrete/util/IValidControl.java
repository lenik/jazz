package net.bodz.lily.concrete.util;

import java.io.IOException;
import java.time.OffsetDateTime;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.meta.decl.NotNull;

public interface IValidControl
        extends
            IJsonForm {

    boolean isValid();

    void setValid(boolean valid);

    OffsetDateTime getValidSince();

    void setValidSince(OffsetDateTime since);

    OffsetDateTime getValidUntil();

    void setValidUntil(OffsetDateTime until);

    @Override
    default void jsonIn(@NotNull JsonObject o, JsonFormOptions opts)
            throws ParseException {
        setValid(o.getBoolean("valid", false));
        setValidSince(o.getOffsetDateTime("validSince"));
        setValidUntil(o.getOffsetDateTime("validUntil"));
    }

    @Override
    default void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException, FormatException {
        out.entry("valid", isValid());
        out.entry("validSince", getValidSince());
        out.entry("validUntil", getValidUntil());
    }

}
