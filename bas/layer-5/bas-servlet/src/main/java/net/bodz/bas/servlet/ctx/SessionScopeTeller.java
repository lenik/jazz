package net.bodz.bas.servlet.ctx;

import net.bodz.bas.ctx.scope.AbstractScopeTeller;
import net.bodz.bas.ctx.scope.IScopeInstance;

import jakarta.servlet.http.HttpSession;

public class SessionScopeTeller
        extends AbstractScopeTeller {

    @Override
    public IScopeInstance tell() {
        HttpSession session = CurrentHttpService.getSessionOpt();
        if (session == null)
            return null;
        return new SessionScopeInstance(session);
    }

    @Override
    public String tellId() {
        HttpSession session = CurrentHttpService.getSessionOpt();
        if (session == null)
            return null;
        else
            return session.getId();
    }

}
