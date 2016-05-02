package net.bodz.bas.http.ctx;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import net.bodz.bas.ctx.scope.IScopeInstance;
import net.bodz.bas.ctx.scope.MutableScopeInstance;

public class SessionScopeInstance
        extends MutableScopeInstance {

    public SessionScopeInstance(HttpSession session) {
        super(session.getId(), session);
    }

    @Override
    protected IScopeInstance getInternalParent(Object identity) {
        HttpSession session = (HttpSession) identity;
        ServletContext servletContext = session.getServletContext();
        return new ServletContextScopeInstance(servletContext);
    }

    public HttpSession getSession() {
        return (HttpSession) getIdentity();
    }

    @Override
    protected Map<String, Object> getVarMap() {
        return null;
    }

    @Override
    public boolean contains(String name) {
        return getSession().getAttribute(name) != null;
    }

    @Override
    public Object get(String name) {
        return getSession().getAttribute(name);
    }

    @Override
    public void set(String name, Object value) {
        getSession().setAttribute(name, value);
    }

    @Override
    public void remove(String name) {
        getSession().removeAttribute(name);
    }

}
