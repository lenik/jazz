package net.bodz.bas.http.ctx;

import javax.servlet.http.HttpSession;

import net.bodz.bas.ctx.scope.AbstractScopeTeller;
import net.bodz.bas.ctx.scope.IScopeInstance;

public class SessionScopeTeller
        extends AbstractScopeTeller {

    @Override
    public IScopeInstance tell() {
        HttpSession session = CurrentHttpService.getSessionOpt();
        if (session == null)
            return null;
        return new SessionScopeInstance(session);
    }

}
