package net.bodz.bas.repr.html;

import javax.servlet.http.HttpServletResponse;

import net.bodz.bas.io.IByteOut;
import net.bodz.bas.io.IPrintOut;

public abstract class AbstractHtmlOutputContext
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
    public IPrintOut getPrintOut() {
        IHtmlOutputContext parent = getParent();
        if (parent == null)
            return IPrintOut.NULL;
        else
            return parent.getPrintOut();
    }

}
