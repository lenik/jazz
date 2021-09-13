package net.bodz.bas.html.viz;

import net.bodz.bas.html.dom.IHtmlHeadData;
import net.bodz.bas.servlet.IHttpViewContext;

public interface IHtmlViewContext
        extends IHttpViewContext {

    <T> IHtmlViewBuilder<T> getViewBuilder(Object obj);

    @Override
    IHtmlHeadData getHeadData();

}
