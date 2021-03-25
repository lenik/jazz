package net.bodz.bas.html.viz;

import java.util.IdentityHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.bodz.bas.html.dom.HtmlHeadData;
import net.bodz.bas.html.dom.IHtmlHeadData;
import net.bodz.bas.servlet.viz.DefaultHttpViewContext;

public class DefaultHtmlViewContext
        extends DefaultHttpViewContext
        implements IHtmlViewContext {

    private IHtmlHeadData headData;
    private Map<Object, IHtmlViewBuilder<?>> viewBuilderCache;

    public DefaultHtmlViewContext(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
        headData = new HtmlHeadData();
        viewBuilderCache = new IdentityHashMap<Object, IHtmlViewBuilder<?>>();
    }

    @Override
    public IHtmlHeadData getHeadData() {
        return headData;
    }

    @Override
    public <T> IHtmlViewBuilder<T> getViewBuilder(Object obj) {
        @SuppressWarnings("unchecked")
        IHtmlViewBuilder<T> viewBuilder = (IHtmlViewBuilder<T>) viewBuilderCache.get(obj);
        return viewBuilder;
    }

}
