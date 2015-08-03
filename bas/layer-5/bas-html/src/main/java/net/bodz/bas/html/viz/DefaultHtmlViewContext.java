package net.bodz.bas.html.viz;

import java.util.IdentityHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.bodz.bas.html.dom.HtmlDoc;
import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.http.viz.DefaultHttpViewContext;

public class DefaultHtmlViewContext
        extends DefaultHttpViewContext
        implements IHtmlViewContext {

    private IHtmlHeadData headData;
    private HtmlDoc htmlDoc;
    private IHtmlTag outerTag;
    private Map<Object, IHtmlViewBuilder<?>> viewBuilderCache;

    public DefaultHtmlViewContext(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
        headData = new HtmlHeadData();
        htmlDoc = new HtmlDoc();
        outerTag = htmlDoc.getRoot();
        viewBuilderCache = new IdentityHashMap<>();
    }

    @Override
    public IHtmlHeadData getHeadData() {
        return headData;
    }

    @Override
    public IHtmlTag getOut() {
        return outerTag;
    }

    @Override
    public void setOut(IHtmlTag outerTag) {
        if (outerTag == null)
            throw new NullPointerException("outerTag");
        this.outerTag = outerTag;
    }

    @Override
    public HtmlDoc getHtmlDoc() {
        return htmlDoc;
    }

    @Override
    public <T> IHtmlViewBuilder<T> getViewBuilder(Object obj) {
        IHtmlViewBuilder<T> viewBuilder = (IHtmlViewBuilder<T>) viewBuilderCache.get(obj);
        return viewBuilder;
    }

}
