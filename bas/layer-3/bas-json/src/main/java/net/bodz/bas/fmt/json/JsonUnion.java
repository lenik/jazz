package net.bodz.bas.fmt.json;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.meta.decl.NotNull;

public class JsonUnion
        implements
            IJsonForm {

    List<IJsonForm> list;

    public JsonUnion(List<IJsonForm> list) {
        if (list == null)
            throw new NullPointerException("list");
        this.list = list;
    }

    public JsonUnion(IJsonForm... array) {
        this(Arrays.asList(array));
    }

    @Override
    public void jsonIn(@NotNull JsonVariant j, JsonFormOptions opts)
            throws ParseException {
        for (IJsonForm item : list)
            item.jsonIn(j, opts);
    }

    @Override
    public final void jsonIn(JsonObject o, JsonFormOptions opts)
            throws ParseException {
        jsonIn(JsonVariant.of(o), opts);
    }

    @Override
    public void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException, FormatException {
        for (IJsonForm item : list)
            item.jsonOut(out, opts);
    }

}
