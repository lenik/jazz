package net.bodz.lily.entity.manager;

import java.io.IOException;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.json.JsonObject;

public class ResolvedEntity
        implements
            IJsonForm {

    public String idFieldStrings[];
    public Object idFields[];
    public Object id;

    public Object entity;

    public String preferredExtension;

    @Override
    public void jsonIn(JsonObject o, JsonFormOptions opts)
            throws ParseException {
    }

    @Override
    public void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException, FormatException {
        if (entity == null) {
            out.entry("error", "null entity.");
            return;
        }
        if (!(entity instanceof IJsonForm)) {
            out.entry("error", "can't convert to JSON: " + entity);
            return;
        }
        IJsonForm jsonEntity = (IJsonForm) entity;
        jsonEntity.jsonOut(out, opts);
    }

    @Override
    public String toString() {
        return String.format("%s: %s", id, entity);
    }

}
