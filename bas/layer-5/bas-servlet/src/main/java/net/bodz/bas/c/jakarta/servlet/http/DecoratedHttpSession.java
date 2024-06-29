package net.bodz.bas.c.jakarta.servlet.http;

import java.util.Enumeration;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionContext;

import net.bodz.bas.c.util.IAttributes;
import net.bodz.bas.t.model.AbstractDecorator;

@SuppressWarnings("deprecation")
public class DecoratedHttpSession
        extends AbstractDecorator<HttpSession>
        implements
            HttpSession,
            IAttributes {

    private static final long serialVersionUID = 1L;

    public DecoratedHttpSession(HttpSession _orig) {
        super(_orig);
    }

    @Override
    public long getCreationTime() {
        return getWrapped().getCreationTime();
    }

    @Override
    public String getId() {
        return getWrapped().getId();
    }

    @Override
    public long getLastAccessedTime() {
        return getWrapped().getLastAccessedTime();
    }

    @Override
    public ServletContext getServletContext() {
        return getWrapped().getServletContext();
    }

    @Override
    public void setMaxInactiveInterval(int interval) {
        getWrapped().setMaxInactiveInterval(interval);
    }

    @Override
    public int getMaxInactiveInterval() {
        return getWrapped().getMaxInactiveInterval();
    }

    @Override
    @Deprecated
    public HttpSessionContext getSessionContext() {
        return getWrapped().getSessionContext();
    }

    @Override
    public Object getAttribute(String name) {
        return getWrapped().getAttribute(name);
    }

    @Override
    @Deprecated
    public Object getValue(String name) {
        return getWrapped().getValue(name);
    }

    @Override
    public Enumeration<String> getAttributeNames() {
        return getWrapped().getAttributeNames();
    }

    @Override
    @Deprecated
    public String[] getValueNames() {
        return getWrapped().getValueNames();
    }

    @Override
    public void setAttribute(String name, Object value) {
        getWrapped().setAttribute(name, value);
    }

    @Override
    @Deprecated
    public void putValue(String name, Object value) {
        getWrapped().putValue(name, value);
    }

    @Override
    public void removeAttribute(String name) {
        getWrapped().removeAttribute(name);
    }

    @Override
    @Deprecated
    public void removeValue(String name) {
        getWrapped().removeValue(name);
    }

    @Override
    public void invalidate() {
        getWrapped().invalidate();
    }

    @Override
    public boolean isNew() {
        return getWrapped().isNew();
    }

}
