package net.bodz.bas.html.viz;

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

    IHtmlHeadData getHeadData();

    Map<String, Object> getAttributeMap();

    <T> T getAttribute(String name);

    void setAttribute(String name, Object value);

    IHtmlTag getOut();

    void setOut(IHtmlTag out);

    /** bufferred */
    HtmlDoc getHtmlDoc();

    IHtmlTag getTag(String id);

    <T> IHtmlViewBuilder<T> getViewBuilder(Object obj);

}
