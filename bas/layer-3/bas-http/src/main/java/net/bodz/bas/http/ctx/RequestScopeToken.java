package net.bodz.bas.http.ctx;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.bodz.bas.ctx.scope.IScopeToken;
import net.bodz.bas.ctx.scope.MutableScopeToken;

public class RequestScopeToken
        extends MutableScopeToken {

    public RequestScopeToken(HttpServletRequest request) {
        super(request.getRequestURI(), request);
    }

    @Override
    protected IScopeToken getInternalParent(Object identity) {
        HttpServletRequest request = (HttpServletRequest) identity;
        HttpSession session = request.getSession();
        return new SessionScopeToken(session);
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
