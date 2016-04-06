package net.bodz.bas.http.ctx;

import javax.servlet.ServletContext;

import net.bodz.bas.ctx.scope.id.MutableScopeDescriptor;

public class ServletContextScopeToken
        extends MutableScopeDescriptor {

    public ServletContextScopeToken(ServletContext serviceContext) {
        super(serviceContext.getServletContextName(), serviceContext);
    }

    public ServletContext getServletContext() {
        return (ServletContext) getIdentity();
    }

    @Override
    public boolean contains(String name) {
        return getServletContext().getAttribute(name) != null;
    }

    @Override
    public Object get(String name) {
        return getServletContext().getAttribute(name);
    }

    @Override
    public void set(String name, Object value) {
        getServletContext().setAttribute(name, value);
    }

}
