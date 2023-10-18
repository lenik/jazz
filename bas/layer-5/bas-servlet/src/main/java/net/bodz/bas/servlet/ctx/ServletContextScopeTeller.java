package net.bodz.bas.servlet.ctx;

import net.bodz.bas.ctx.scope.AbstractScopeTeller;
import net.bodz.bas.ctx.scope.IScopeInstance;

import jakarta.servlet.ServletContext;

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
