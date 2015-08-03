package net.bodz.bas.http.viz;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class DefaultHttpViewContext
        extends AbstractHttpViewContext
        implements IHttpViewContext {

    private final HttpServletRequest request;
    private final HttpServletResponse response;

    private Map<String, Object> attributes;

    public DefaultHttpViewContext(HttpServletRequest request, HttpServletResponse response) {
        if (request == null)
            throw new NullPointerException("request");
        if (response == null)
            throw new NullPointerException("response");
        this.request = request;
        this.response = response;

        attributes = new HashMap<String, Object>();
    }

    @Override
    public IHttpViewContext getRoot() {
        return this;
    }

    @Override
    public IHttpViewContext getParent() {
        return null;
    }

    @Override
    public HttpServletRequest getRequest() {
        return request;
    }

    @Override
    public HttpServletResponse getResponse() {
        return response;
    }

    @Override
    public final HttpSession getSession() {
        if (request == null)
            return null;
        else
            return request.getSession();
    }

    @Override
    public Map<String, Object> getAttributeMap() {
        return attributes;
    }

    @Override
    public <T> T getAttribute(String name) {
        return (T) attributes.get(name);
    }

    @Override
    public void setAttribute(String name, Object value) {
        if (name == null)
            throw new NullPointerException("name");
        attributes.put(name, value);
    }

}
