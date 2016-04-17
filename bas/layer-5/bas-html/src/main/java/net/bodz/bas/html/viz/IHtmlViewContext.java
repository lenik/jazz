package net.bodz.bas.html.viz;

import net.bodz.bas.http.viz.IHttpViewContext;

public interface IHtmlViewContext
        extends IHttpViewContext {

    <T> IHtmlViewBuilder<T> getViewBuilder(Object obj);

    IHtmlHeadData getHeadData();

}
