package net.bodz.bas.http.ctx;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import net.bodz.bas.context.IContext;
import net.bodz.bas.context.MutableContext;

public class SessionContext
        extends MutableContext {

    public SessionContext(HttpSession session) {
        super(session.getId(), session);
    }

    @Override
    protected IContext getInternalParent(Object identity) {
        HttpSession session = (HttpSession) identity;
        ServletContext servletContext = session.getServletContext();
        return new ServletContextContext(servletContext);
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
