package net.bodz.bas.http.ctx;

import javax.servlet.ServletContext;

import net.bodz.bas.ctx.scope.AbstractScopeTeller;
import net.bodz.bas.ctx.scope.IScopeInstance;

public class ServletContextScopeTeller
        extends AbstractScopeTeller {

    @Override
    public IScopeInstance tell() {
        ServletContext context = CurrentHttpService.getServletContextOpt();
        if (context == null)
            return null;
        else
            return new ServletContextScopeInstance(context);
    }

    @Override
    public String tellId() {
        ServletContext context = CurrentHttpService.getServletContextOpt();
        if (context == null)
            return null;
        else
            return context.getContextPath();
    }

}
