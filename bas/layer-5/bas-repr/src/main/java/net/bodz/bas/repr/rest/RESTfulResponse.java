package net.bodz.bas.repr.rest;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class RESTfulResponse
        extends HttpServletResponseWrapper
        implements IRESTfulResponse {

    private Object target;
    private Throwable exception;
    private String method;

    public RESTfulResponse(HttpServletResponse response) {
        super(response);
    }

    @Override
    public Object getTarget() {
        return target;
    }

    @Override
    public void setTarget(Object target) {
        this.target = target;
    }

    @Override
    public Throwable getException() {
        return exception;
    }

    @Override
    public void setException(Throwable exception) {
        this.exception = exception;
    }

    @Override
    public String getMethod() {
        return method;
    }

    @Override
    public void setMethod(String method) {
        this.method = method;
    }

}
