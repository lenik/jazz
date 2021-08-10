package net.bodz.bas.servlet.man;

import javax.servlet.http.HttpSession;

import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.IPathDispatchable;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.path.PathDispatchException;
import net.bodz.bas.t.variant.IVariantMap;

public class SessionManager
        implements IPathDispatchable {

    HttpSession session;

    public SessionManager(HttpSession session) {
        if (session == null)
            throw new NullPointerException("session");
        this.session = session;
    }

    @Override
    public IPathArrival dispatch(IPathArrival previous, ITokenQueue tokens, IVariantMap<String> q)
            throws PathDispatchException {
        return null;
    }

}
