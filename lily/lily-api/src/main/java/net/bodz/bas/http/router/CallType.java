package net.bodz.bas.http.router;

import java.io.IOException;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.meta.decl.NotNull;

public class CallType
        implements
            IJsonForm {

    @Override
    public void jsonIn(@NotNull JsonObject o, JsonFormOptions opts)
            throws ParseException {
    }

    @Override
    public void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException, FormatException {
    }

}
