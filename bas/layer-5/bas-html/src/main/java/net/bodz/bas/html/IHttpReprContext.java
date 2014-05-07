package net.bodz.bas.html;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.bodz.bas.io.IByteOut;
import net.bodz.bas.io.html.IHtmlOut;
import net.bodz.bas.meta.decl.Shortcut;

public interface IHttpReprContext
        extends IRestfulReprContext {

    IHttpReprContext getParent();

    @Shortcut("parent/.../parent")
    IHttpReprContext getRoot();

    HttpServletRequest getRequest();

    HttpServletResponse getResponse();

    @Shortcut("getRequest().getSession()")
    HttpSession getSession();

    IByteOut getByteOut()
            throws IOException;

    IHtmlOut getOut()
            throws IOException;

    void flush()
            throws IOException;

}
