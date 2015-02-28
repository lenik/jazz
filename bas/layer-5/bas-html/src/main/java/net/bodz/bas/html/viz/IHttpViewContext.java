package net.bodz.bas.html.viz;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.bodz.bas.html.dom.HtmlDoc;
import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.meta.decl.Shortcut;
import net.bodz.bas.rtx.IQueryable;

public interface IHttpViewContext
        extends IQueryable {

    IHttpViewContext getParent();

    @Shortcut("parent/.../parent")
    IHttpViewContext getRoot();

    HttpServletRequest getRequest();

    HttpServletResponse getResponse();

    HttpSession getSession();

    IHtmlHeadData getHeadData();

    Map<String, Object> getAttributeMap();

    <T> T getAttribute(String name);

    void setAttribute(String name, Object value);

    IHtmlTag getOut();

    void setOut(IHtmlTag out);

    /** bufferred */
    HtmlDoc getHtmlDoc();

    <T> IHttpViewBuilder<T> getViewBuilder(Object obj);

}
