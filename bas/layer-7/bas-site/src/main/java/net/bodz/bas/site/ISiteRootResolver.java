package net.bodz.bas.site;

import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.IPathDispatchable;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.path.PathDispatchException;
import net.bodz.bas.servlet.ctx.CurrentHttpService;
import net.bodz.bas.t.variant.IVariantMap;

import jakarta.servlet.http.HttpServletRequest;

public interface ISiteRootResolver
        extends
            IPathDispatchable {

    ISiteRoot getSiteRoot(HttpServletRequest request);

    @Override
    default IPathArrival dispatch(IPathArrival previous, ITokenQueue tokens, IVariantMap<String> q)
            throws PathDispatchException {
        HttpServletRequest req = CurrentHttpService.getRequest();
        ISiteRoot root = getSiteRoot(req);
        if (root == null)
            return null;
        else
            return root.dispatch(previous, tokens, q);
    }

}
