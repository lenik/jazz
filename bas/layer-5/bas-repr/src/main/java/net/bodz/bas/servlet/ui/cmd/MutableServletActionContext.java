package net.bodz.bas.servlet.ui.cmd;

import net.bodz.bas.ui.model.action.MutableActionContext;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

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
