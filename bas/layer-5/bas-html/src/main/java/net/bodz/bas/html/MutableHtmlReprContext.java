package net.bodz.bas.html;

import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.req.IMethodOfRequest;
import net.bodz.bas.repr.req.IResultOfRequest;
import net.bodz.bas.repr.req.IViewOfRequest;

/**
 * Not used.
 */
@Deprecated
public class MutableHtmlReprContext
        implements IRestfulReprContext {

    ITokenQueue tokenQueue;
    IMethodOfRequest methodOfRequest;
    IResultOfRequest resultOfRequest;
    IViewOfRequest viewOfRequest;

    public MutableHtmlReprContext(IRestfulReprContext o) {
        if (o == null)
            throw new NullPointerException("o");
        tokenQueue = o.getTokenQueue();
        methodOfRequest = o.getMethodOfRequest();
        resultOfRequest = o.getResultOfRequest();
        viewOfRequest = o.getViewOfRequest();
    }

    public MutableHtmlReprContext(MutableHtmlReprContext o) {
        if (o == null)
            throw new NullPointerException("o");
        tokenQueue = o.tokenQueue;
        methodOfRequest = o.methodOfRequest;
        resultOfRequest = o.resultOfRequest;
        viewOfRequest = o.viewOfRequest;
    }

    @Override
    public ITokenQueue getTokenQueue() {
        return tokenQueue;
    }

    public void setTokenQueue(ITokenQueue tokenQueue) {
        this.tokenQueue = tokenQueue;
    }

    @Override
    public IMethodOfRequest getMethodOfRequest() {
        return methodOfRequest;
    }

    public void setMethodOfRequest(IMethodOfRequest methodOfRequest) {
        this.methodOfRequest = methodOfRequest;
    }

    @Override
    public IResultOfRequest getResultOfRequest() {
        return resultOfRequest;
    }

    public void setResultOfRequest(IResultOfRequest resultOfRequest) {
        this.resultOfRequest = resultOfRequest;
    }

    @Override
    public IViewOfRequest getViewOfRequest() {
        return viewOfRequest;
    }

    public void setViewOfRequest(IViewOfRequest viewOfRequest) {
        this.viewOfRequest = viewOfRequest;
    }

}
