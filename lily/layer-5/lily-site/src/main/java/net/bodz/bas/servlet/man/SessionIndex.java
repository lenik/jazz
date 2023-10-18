package net.bodz.bas.servlet.man;

import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.IPathDispatchable;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.path.PathArrival;
import net.bodz.bas.repr.path.PathDispatchException;
import net.bodz.bas.servlet.ctx.CurrentHttpService;
import net.bodz.bas.t.variant.IVariantMap;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class SessionIndex
        implements IPathDispatchable {

    @Override
    public IPathArrival dispatch(IPathArrival previous, ITokenQueue tokens, IVariantMap<String> q)
            throws PathDispatchException {
        HttpServletRequest request = CurrentHttpService.getRequest();

        String token = tokens.peek();
        if (token == null)
            return null;

        String sessionId = token;
        ServletContext servletContext = request.getServletContext();
        SessionRegistry sessionRegistry = SessionRegistry.fromServletContext(servletContext);
        HttpSession session = sessionRegistry.getSession(sessionId);
        if (session == null)
            return null;

        SessionManager target = new SessionManager(session);
        return PathArrival.shift(previous, this, target, tokens);
    }

}
