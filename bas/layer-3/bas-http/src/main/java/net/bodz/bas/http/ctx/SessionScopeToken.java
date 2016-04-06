package net.bodz.bas.http.ctx;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import net.bodz.bas.ctx.scope.id.IScopeDescriptor;
import net.bodz.bas.ctx.scope.id.MutableScopeDescriptor;

public class SessionScopeToken
        extends MutableScopeDescriptor {

    public SessionScopeToken(HttpSession session) {
        super(session.getId(), session);
    }

    @Override
    protected IScopeDescriptor getInternalParent(Object identity) {
        HttpSession session = (HttpSession) identity;
        ServletContext servletContext = session.getServletContext();
        return new ServletContextScopeToken(servletContext);
    }

    public HttpSession getSession() {
        return (HttpSession) getIdentity();
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

}
