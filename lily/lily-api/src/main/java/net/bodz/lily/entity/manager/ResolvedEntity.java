package net.bodz.lily.entity.manager;

import java.io.IOException;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.IPathDispatchable;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.path.PathDispatchException;
import net.bodz.bas.t.variant.IVariantMap;

public class ResolvedEntity
        implements
            IPathDispatchable,
            IJsonForm {

    public String idFieldStrings[];
    public Object idFields[];
    public Object id;

    public Object entity;

    public String preferredExtension;

    @Override
    public IPathArrival dispatch(IPathArrival previous, ITokenQueue tokens, IVariantMap<String> q)
            throws PathDispatchException {
        if (entity instanceof IPathDispatchable) {
            IPathDispatchable pd = (IPathDispatchable) entity;
            return pd.dispatch(previous, tokens, q);
        }
        return null;
    }

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
