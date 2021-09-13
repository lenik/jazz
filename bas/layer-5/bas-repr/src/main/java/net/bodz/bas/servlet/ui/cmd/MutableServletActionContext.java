package net.bodz.bas.servlet.ui.cmd;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.bodz.bas.ui.model.action.MutableActionContext;

public class MutableServletActionContext
        extends MutableActionContext
        implements IServletActionContext {

    HttpServletRequest request;
    HttpServletResponse response;

    public MutableServletActionContext(HttpServletRequest request, HttpServletResponse response) {
        if (request == null)
            throw new NullPointerException("request");
        if (response == null)
            throw new NullPointerException("response");
        this.request = request;
        this.response = response;
    }

    @Override
    public ServletContext getServletContext() {
        return request.getServletContext();
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
    public HttpSession getSession() {
        return request.getSession();
    }

}
