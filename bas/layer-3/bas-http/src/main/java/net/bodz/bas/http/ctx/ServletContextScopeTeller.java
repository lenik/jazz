package net.bodz.bas.http.ctx;

import javax.servlet.http.HttpServletRequest;

import net.bodz.bas.ctx.scope.AbstractScopeTeller;
import net.bodz.bas.ctx.scope.IScopeInstance;

public class ServletContextScopeTeller
        extends AbstractScopeTeller {

    @Override
    public IScopeInstance tell() {
        HttpServletRequest request = CurrentHttpService.getRequestOpt();
        if (request == null)
            return null;
        return new ServletContextScopeInstance(request.getServletContext());
    }

}
