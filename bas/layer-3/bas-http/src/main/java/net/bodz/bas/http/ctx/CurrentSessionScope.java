package net.bodz.bas.http.ctx;

import javax.servlet.http.HttpSession;

import net.bodz.bas.ctx.scope.AbstractScope;
import net.bodz.bas.ctx.scope.id.IScopeDescriptor;

public class CurrentSessionScope
        extends AbstractScope {

    @Override
    public IScopeDescriptor tell() {
        IScopeDescriptor parent = IScopeDescriptor.DEFAULT;

        HttpSession session = CurrentHttpService.getSessionOpt();
        if (session != null) {
            SessionScopeToken token = new SessionScopeToken(session);
            token.setParent(parent);
            parent = token;
        }

        return parent;
    }

    @Override
    public boolean contains(String name) {
        HttpSession session = CurrentHttpService.getSessionOpt();
        return session.getAttribute(name) != null;
    }

    @Override
    public Object resolve(String name) {
        return null;
    }

}
