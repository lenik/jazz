package net.bodz.bas.servlet.ctx;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.bodz.bas.ctx.scope.IScopeInstance;
import net.bodz.bas.ctx.scope.MutableScopeInstance;

public class RequestScopeInstance
        extends MutableScopeInstance {

    public RequestScopeInstance(HttpServletRequest request) {
        super(request.getRequestURI(), request);
    }

    @Override
    protected IScopeInstance getInternalParent(Object identity) {
        HttpServletRequest request = (HttpServletRequest) identity;
        HttpSession session = request.getSession();
        return new SessionScopeInstance(session);
    }

    public HttpServletRequest getRequest() {
        return (HttpServletRequest) getIdentity();
    }

    @Override
    protected Map<String, Object> getVarMap() {
        return null;
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

    @Override
    public void remove(String name) {
        getRequest().removeAttribute(name);
    }

}
