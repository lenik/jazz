package net.bodz.bas.html;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.bodz.bas.io.IByteOut;
import net.bodz.bas.io.html.IHtmlOut;

/**
 * Not used.
 */
@Deprecated
public class MutableHtmlOutputContext
        extends MutableHtmlReprContext
        implements IHttpReprContext {

    IHttpReprContext parent;
    HttpServletRequest request;
    HttpServletResponse response;
    IByteOut byteOut;
    IHtmlOut htmlOut;

    public MutableHtmlOutputContext(IHttpReprContext o) {
        super(o);
        parent = o.getParent();
        request = o.getRequest();
        response = o.getResponse();
        byteOut = o.getByteOut();
        htmlOut = o.getOut();
    }

    public MutableHtmlOutputContext(MutableHtmlOutputContext o) {
        super(o);
    }

    @Override
    public IHttpReprContext getParent() {
        return parent;
    }

    public void setParent(IHttpReprContext parent) {
        this.parent = parent;
    }

    @Override
    public final IHttpReprContext getRoot() {
        IHttpReprContext parent = getParent();
        if (parent == null)
            return this;
        else
            return parent.getRoot();
    }

    @Override
    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    @Override
    public final HttpSession getSession() {
        if (request == null)
            return null;
        else
            return request.getSession();
    }

    @Override
    public IByteOut getByteOut() {
        return byteOut;
    }

    public void setByteOut(IByteOut byteOut) {
        this.byteOut = byteOut;
    }

    @Override
    public IHtmlOut getOut() {
        return htmlOut;
    }

    public void setOut(IHtmlOut htmlOut) {
        this.htmlOut = htmlOut;
    }

    @Override
    public void flush() {
        htmlOut.endAll();
    }

}
