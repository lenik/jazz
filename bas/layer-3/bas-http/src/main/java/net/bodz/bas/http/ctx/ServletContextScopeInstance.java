package net.bodz.bas.http.ctx;

import java.util.Map;

import javax.servlet.ServletContext;

import net.bodz.bas.ctx.scope.MutableScopeInstance;

public class ServletContextScopeInstance
        extends MutableScopeInstance {

    public ServletContextScopeInstance(ServletContext serviceContext) {
        super(serviceContext.getServletContextName(), serviceContext);
    }

    public ServletContext getServletContext() {
        return (ServletContext) getIdentity();
    }

    @Override
    protected Map<String, Object> getVarMap() {
        return null;
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

    @Override
    public void remove(String name) {
        getServletContext().removeAttribute(name);
    }

}
