package net.bodz.bas.http.ctx;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.bodz.bas.ctx.scope.id.IScopeDescriptor;
import net.bodz.bas.ctx.scope.id.MutableScopeDescriptor;

public class RequestScopeDescriptor
        extends MutableScopeDescriptor {

    public RequestScopeDescriptor(HttpServletRequest request) {
        super(request.getRequestURI(), request);
    }

    @Override
    protected IScopeDescriptor getInternalParent(Object identity) {
        HttpServletRequest request = (HttpServletRequest) identity;
        HttpSession session = request.getSession();
        return new SessionScopeDescriptor(session);
    }

    public HttpServletRequest getRequest() {
        return (HttpServletRequest) getIdentity();
    }

    @Override
    public boolean contains(String name) {
        return getRequest().getAttribute(name) != null;
    }

    @Override
    public Object get(String name) {
        return getRequest().getAttribute(name);
    }

    @Override
    public void set(String name, Object value) {
        getRequest().setAttribute(name, value);
    }

}
