package net.bodz.bas.repr.path.builtin;

import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.bas.json.JsonArray;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.json.JsonVariantTreeResolveResult;
import net.bodz.bas.json.JsonVariantTreeResolver;
import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.IPathDispatcher;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.path.PathArrival;
import net.bodz.bas.repr.path.PathDispatchException;
import net.bodz.bas.t.variant.IVariantMap;

public class JsonVariantPathDispatcher
        implements
            IPathDispatcher {

    public static final int PRIORITY = BuiltinPathDispatcherPriorities.PRIORITY_JSON;

    @Override
    public int getPriority() {
        return PRIORITY;
    }

    @Override
    public IPathArrival dispatch(IPathArrival previous, Object source, ITokenQueue tokens, IVariantMap<String> q)
            throws PathDispatchException {
        if (! (source instanceof JsonVariant)) {
            if (source instanceof JsonObject)
                source = JsonVariant.of((JsonObject) source);
            else if (source instanceof JsonArray)
                source = JsonVariant.of((JsonArray) source);
            else
                return null;
        }

        JsonVariant jv = (JsonVariant) source;

        int n = tokens.available();
        String[] lav = tokens.peek(n);

        JsonVariantTreeResolveResult result = JsonVariantTreeResolver.resolveVerbose(jv, lav);

        return PathArrival.shift(result.validCount, previous, this, result.stoppedAt, tokens);
    }

}
