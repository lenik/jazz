package net.bodz.bas.repr.path.builtin;

import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.json.JsonFn;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.json.JsonObjectTreeResolveResult;
import net.bodz.bas.json.JsonObjectTreeResolver;
import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.IPathDispatcher;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.path.PathArrival;
import net.bodz.bas.repr.path.PathDispatchException;
import net.bodz.bas.t.variant.IVariantMap;

public class JsonFormPathDispatcher
        implements
            IPathDispatcher {

    @Override
    public IPathArrival dispatch(IPathArrival previous, Object source, ITokenQueue tokens, IVariantMap<String> q)
            throws PathDispatchException {
        if (!(source instanceof IJsonForm))
            return null;

        IJsonForm jsonForm = (IJsonForm) source;
        JsonObject jo;
        try {
            jo = JsonFn.toJsonObject(jsonForm);
        } catch (Exception e) {
            throw new PathDispatchException(String.format(//
                    "Can't convert %s to JSON: %s", source, e.getMessage()), e);
        }

        int n = tokens.available();
        String[] lav = tokens.peek(n);

        JsonObjectTreeResolveResult result = JsonObjectTreeResolver.resolveVerbose(jo, lav);

        return PathArrival.shift(result.validCount, previous, this, result.stoppedAt, tokens);
    }

}
