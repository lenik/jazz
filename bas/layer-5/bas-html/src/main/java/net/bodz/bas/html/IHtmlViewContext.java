package net.bodz.bas.html;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.bodz.bas.html.dom.HtmlDoc;
import net.bodz.bas.html.dom.IHtmlTag;
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

    Map<String, Object> getAttributeMap();

    Object getAttribute(String name);

    void setAttribute(String name, Object value);

    HtmlDoc getHtmlDoc();

    IHtmlTag getOut();

    void setOut(IHtmlTag out);

    IHtmlTag getAnchor(String name);

    void setAnchor(String name, IHtmlTag anchor);

    <T> IHtmlViewBuilder<T> getViewBuilder(Object obj);

}
