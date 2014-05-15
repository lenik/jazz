package net.bodz.bas.html;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.bodz.bas.io.IByteOut;
import net.bodz.bas.io.html.IHtmlOut;
import net.bodz.bas.meta.decl.Shortcut;
import net.bodz.bas.rtx.IQueryable;

public interface IHtmlViewContext
        extends IQueryable {

    IHtmlViewContext getParent();

    @Shortcut("parent/.../parent")
    IHtmlViewContext getRoot();

    HttpServletRequest getRequest();

    HttpServletResponse getResponse();

    HttpSession getSession();

    IHtmlMetaData getMetaData();

    <T> IHtmlViewBuilder<T> getViewBuilder(Object obj);

    IByteOut getByteOut()
            throws IOException;

    IHtmlOut getOut()
            throws IOException;

    void flush()
            throws IOException;
}
