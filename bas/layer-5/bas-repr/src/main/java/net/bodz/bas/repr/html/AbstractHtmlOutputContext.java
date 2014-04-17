package net.bodz.bas.repr.html;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.bodz.bas.io.IByteOut;
import net.bodz.bas.io.html.IHtmlOut;

public abstract class AbstractHtmlOutputContext
        extends AbstractHtmlReprContext
        implements IHtmlOutputContext {

    @Override
    public IHtmlOutputContext getRoot() {
        IHtmlOutputContext parent = getParent();
        if (parent == null)
            return this;
        else
            return parent.getRoot();
    }

    @Override
    public HttpServletRequest getRequest() {
        IHtmlOutputContext parent = getParent();
        if (parent == null)
            return null;
        else
            return parent.getRequest();
    }

    @Override
    public HttpServletResponse getResponse() {
        IHtmlOutputContext parent = getParent();
        if (parent == null)
            return null;
        else
            return parent.getResponse();
    }

    @Override
    public IByteOut getByteOut() {
        IHtmlOutputContext parent = getParent();
        if (parent == null)
            return IByteOut.NULL;
        else
            return parent.getByteOut();
    }

    @Override
    public IHtmlOut getOut() {
        IHtmlOutputContext parent = getParent();
        if (parent == null)
            return IHtmlOut.NULL;
        else
            return parent.getOut();
    }

    @Override
    public void flush() {
        IHtmlOutputContext parent = getParent();
        if (parent != null)
            parent.flush();
    }

}
