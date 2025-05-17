package net.bodz.bas.http.router;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import net.bodz.bas.c.string.StringPred;
import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.IPathDispatchable;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.path.PathArrival;
import net.bodz.bas.repr.path.PathDispatchException;
import net.bodz.bas.t.variant.IVariantMap;

public class CallManager
        implements
            IPathDispatchable,
            IJsonForm {

//    PacketQueue<CallRequest> requestQueue;
//    PacketQueue<CallResponse> responseQueue;

    private Map<Long, CallRequest> map = new LinkedHashMap<>();
    private long nextRequestId = 1;

    public synchronized CallRequest newCall() {
        CallType type = new CallType();
        long id = nextRequestId++;
        CallRequest request = new CallRequest(type, id);
        map.put(id, request);
        return request;
    }

    public CallRequest get(long id) {
        return map.get(id);
    }

    public void push(CallRequest request) {
    }

    public CallResponse waitForResponse(CallRequest request) {
        return null;
    }

    @Override
    public IPathArrival dispatch(IPathArrival previous, ITokenQueue tokens, IVariantMap<String> q)
            throws PathDispatchException {
        String token = tokens.peek();
        if (token == null)
            return null;

        if (StringPred.isDecimal(token)) {
            long id = Long.parseLong(token);
            if (! map.containsKey(id))
                return null;
            CallRequest request = map.get(id);
            return PathArrival.shift(previous, this, request, tokens);
        }

        switch (token) {
        }
        return null;
    }

    @Override
    public void jsonIn(@NotNull JsonObject o, JsonFormOptions opts)
            throws ParseException {
    }

    @Override
    public void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException, FormatException {
    }

}
