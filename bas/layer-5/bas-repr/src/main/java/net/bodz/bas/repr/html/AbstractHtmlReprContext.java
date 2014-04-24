package net.bodz.bas.repr.html;

import javax.servlet.http.HttpServletRequest;

import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.req.IMethodOfRequest;
import net.bodz.bas.repr.req.IResultOfRequest;
import net.bodz.bas.repr.req.IViewOfRequest;

public abstract class AbstractHtmlReprContext
        implements IRestfulReprContext {

    @Override
    public ITokenQueue getTokenQueue() {
        HttpServletRequest request = getRequest();
        return (ITokenQueue) request.getAttribute(ITokenQueue.ATTRIBUTE_KEY);
    }

    @Override
    public IMethodOfRequest getMethodOfRequest() {
        HttpServletRequest request = getRequest();
        return (IMethodOfRequest) request.getAttribute(IMethodOfRequest.ATTRIBUTE_KEY);
    }

    @Override
    public IResultOfRequest getResultOfRequest() {
        HttpServletRequest request = getRequest();
        return (IResultOfRequest) request.getAttribute(IResultOfRequest.ATTRIBUTE_KEY);
    }

    @Override
    public IViewOfRequest getViewOfRequest() {
        HttpServletRequest request = getRequest();
        return (IViewOfRequest) request.getAttribute(IViewOfRequest.ATTRIBUTE_KEY);
    }

    protected abstract HttpServletRequest getRequest();

}
