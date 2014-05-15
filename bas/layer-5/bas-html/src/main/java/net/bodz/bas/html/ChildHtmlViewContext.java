package net.bodz.bas.html;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.bodz.bas.io.IByteOut;
import net.bodz.bas.io.html.IHtmlOut;
import net.bodz.bas.rtx.QueryException;

public abstract class ChildHtmlViewContext
        implements IHtmlViewContext {

    @Override
    public Object query(Object specification)
            throws QueryException {
        IHtmlViewContext parent = getParent();
        if (parent == null)
            return null;
        else
            return parent.query(specification);
    }

    @Override
    public Object query(String specificationId)
            throws QueryException {
        IHtmlViewContext parent = getParent();
        if (parent == null)
            return null;
        else
            return parent.query(specificationId);
    }

    @Override
    public <spec_t> spec_t query(Class<spec_t> specificationType)
            throws QueryException {
        IHtmlViewContext parent = getParent();
        if (parent == null)
            return null;
        else
            return parent.query(specificationType);
    }

    @Override
    public IHtmlViewContext getRoot() {
        IHtmlViewContext parent = getParent();
        if (parent == null)
            return this;
        else
            return parent.getRoot();
    }

    @Override
    public HttpServletRequest getRequest() {
        IHtmlViewContext parent = getParent();
        if (parent == null)
            return null;
        else
            return parent.getRequest();
    }

    @Override
    public HttpServletResponse getResponse() {
        IHtmlViewContext parent = getParent();
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
    public IByteOut getByteOut()
            throws IOException {
        IHtmlViewContext parent = getParent();
        if (parent == null)
            return IByteOut.NULL;
        else
            return parent.getByteOut();
    }

    @Override
    public IHtmlOut getOut()
            throws IOException {
        IHtmlViewContext parent = getParent();
        if (parent == null)
            return IHtmlOut.NULL;
        else
            return parent.getOut();
    }

    @Override
    public void flush()
            throws IOException {
        IHtmlViewContext parent = getParent();
        if (parent != null)
            parent.flush();
    }

}
