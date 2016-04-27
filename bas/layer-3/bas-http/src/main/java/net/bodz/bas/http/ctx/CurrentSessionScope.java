package net.bodz.bas.http.ctx;

import javax.servlet.http.HttpSession;

import net.bodz.bas.ctx.scope.AbstractScope;
import net.bodz.bas.ctx.scope.id.IScopeDescriptor;

public class CurrentSessionScope
        extends AbstractScope {

    @Override
    public IScopeDescriptor tell() {
        IScopeDescriptor current = IScopeDescriptor.DEFAULT;

        HttpSession session = CurrentHttpService.getSessionOpt();
        if (session != null) {
            SessionScopeDescriptor descriptor = new SessionScopeDescriptor(session);
            descriptor.setParent(current);
            current = descriptor;
        }

        return current;
    }

}
