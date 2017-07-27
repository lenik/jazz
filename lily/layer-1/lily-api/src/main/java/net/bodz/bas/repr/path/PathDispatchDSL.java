package net.bodz.bas.repr.path;

import javax.servlet.http.HttpServletRequest;

import net.bodz.bas.http.ctx.CurrentHttpService;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.bas.t.variant.VariantMaps;

public class PathDispatchDSL
        implements JavaDSLContext {

    protected IPathArrival previous;
    protected ITokenQueue tokens;
    protected String token;

    protected HttpServletRequest request;
    protected IVariantMap<String> q;
    protected Object target;

    public PathDispatchDSL(IPathArrival previous, ITokenQueue tokens)
            throws PathDispatchException {
        this.previous = previous;
        this.tokens = tokens;

        token = tokens.peek();
        request = CurrentHttpService.getRequest();
        q = VariantMaps.fromRequest(request);
        target = null;
    }

    public IPathArrival dispatch() {
        if (target == null)
            return null;
        else
            return PathArrival.shift(previous, target, tokens);
    }

}