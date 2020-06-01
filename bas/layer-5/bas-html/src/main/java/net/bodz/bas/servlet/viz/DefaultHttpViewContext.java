package net.bodz.bas.servlet.viz;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.bodz.bas.ctx.util.IFramedMap;
import net.bodz.bas.ctx.util.StrictFramedMap;
import net.bodz.bas.servlet.model.IHttpHeadData;

public class DefaultHttpViewContext
        extends AbstractHttpViewContext
        implements IHttpViewContext {

    private final HttpServletRequest request;
    private final HttpServletResponse response;
    private boolean stopped;

    private final Map<String, Object> attributes;
    private final IFramedMap<String, Object> varMap;

    private IHttpHeadData headData;

    public DefaultHttpViewContext(HttpServletRequest request, HttpServletResponse response) {
        if (request == null)
            throw new NullPointerException("request");
        if (response == null)
            throw new NullPointerException("response");
        this.request = request;
        this.response = response;

        attributes = new HashMap<String, Object>();
        varMap = new StrictFramedMap<String, Object>();
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
    public <T> T stop() {
        this.stopped = true;
        return null;
    }

    @Override
    public boolean isStopped() {
        return stopped;
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

    @Override
    public IFramedMap<String, Object> getVariableMap() {
        return varMap;
    }

    @Override
    public <T> T getVariable(String name) {
        return varMap._get(name, null);
    }

    @Override
    public void setVariable(String name, Object value) {
        varMap.put(name, value);
    }

    @Override
    public Object removeVariable(String name) {
        return varMap.remove(name);
    }

    @Override
    public IHttpHeadData getHeadData() {
        return headData;
    }

}
