package net.bodz.bas.repr.html;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.bodz.bas.io.IByteOut;
import net.bodz.bas.io.html.IHtmlOut;

public abstract class AbstractHtmlOutputContext
        extends AbstractHtmlReprContext
        implements IHttpReprContext {

    @Override
    public IHttpReprContext getRoot() {
        IHttpReprContext parent = getParent();
        if (parent == null)
            return this;
        else
            return parent.getRoot();
    }

    @Override
    public HttpServletRequest getRequest() {
        IHttpReprContext parent = getParent();
        if (parent == null)
            return null;
        else
            return parent.getRequest();
    }

    @Override
    public HttpServletResponse getResponse() {
        IHttpReprContext parent = getParent();
        if (parent == null)
            return null;
        else
            return parent.getResponse();
    }

    @Override
    public HttpSession getSession() {
        HttpServletRequest req = getRequest();
        if (req == null)
            return null;
        else
            return req.getSession();
    }

    @Override
    public IByteOut getByteOut() {
        IHttpReprContext parent = getParent();
        if (parent == null)
            return IByteOut.NULL;
        else
            return parent.getByteOut();
    }

    @Override
    public IHtmlOut getOut() {
        IHttpReprContext parent = getParent();
        if (parent == null)
            return IHtmlOut.NULL;
        else
            return parent.getOut();
    }

    @Override
    public void flush() {
        IHttpReprContext parent = getParent();
        if (parent != null)
            parent.flush();
    }

}
