package net.bodz.bas.servlet.man;

import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.IPathDispatchable;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.path.PathArrival;
import net.bodz.bas.repr.path.PathDispatchException;
import net.bodz.bas.t.variant.IVariantMap;

public class SysManager
        implements IPathDispatchable {

    @Override
    public IPathArrival dispatch(IPathArrival previous, ITokenQueue tokens, IVariantMap<String> q)
            throws PathDispatchException {
        Object target = null;
        String token = tokens.peek();
        if (token == null)
            return null;

        switch (token) {
        case "servletContext":
            target = new ServletContextManager();
            break;

        case "session":
            target = new SessionIndex();
            break;
        }

        if (target != null)
            return PathArrival.shift(previous, this, target, tokens);
        return null;
    }

}
