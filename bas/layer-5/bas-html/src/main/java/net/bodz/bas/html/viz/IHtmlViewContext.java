package net.bodz.bas.html.viz;

import net.bodz.bas.html.dom.HtmlDoc;
import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.http.viz.IHttpViewContext;

public interface IHtmlViewContext
        extends IHttpViewContext {

    <T> IHtmlViewBuilder<T> getViewBuilder(Object obj);

    IHtmlHeadData getHeadData();

    IHtmlTag getOut();

    void setOut(IHtmlTag out);

    /** bufferred */
    HtmlDoc getHtmlDoc();

}
