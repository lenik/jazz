package net.bodz.bas.html.viz;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.bodz.bas.html.dom.HtmlDoc;
import net.bodz.bas.html.dom.IHtmlTag;

public class DefaultHttpViewContext
        extends AbstractHttpViewContext
        implements IHttpViewContext {

    private final HttpServletRequest request;
    private final HttpServletResponse response;

    private IHtmlHeadData headData;
    private Map<String, Object> attributes;

    private HtmlDoc htmlDoc;
    private IHtmlTag outerTag;

    private Map<Object, IHttpViewBuilder<?>> viewBuilderCache;

    public DefaultHttpViewContext(HttpServletRequest request, HttpServletResponse response) {
        if (request == null)
            throw new NullPointerException("request");
        if (response == null)
            throw new NullPointerException("response");
        this.request = request;
        this.response = response;

        headData = new HtmlHeadData();
        attributes = new HashMap<String, Object>();

        htmlDoc = new HtmlDoc();
        outerTag = htmlDoc.getRoot();

        viewBuilderCache = new IdentityHashMap<>();
    }

    @Override
    public IHttpViewContext getRoot() {
        return this;
    }

    @Override
    public IHttpViewContext getParent() {
        return null;
    }

    @Override
    public HttpServletRequest getRequest() {
        return request;
    }

    @Override
    public HttpServletResponse getResponse() {
        return response;
    }

    @Override
    public final HttpSession getSession() {
        if (request == null)
            return null;
        else
            return request.getSession();
    }

    @Override
    public IHtmlHeadData getHeadData() {
        return headData;
    }

    @Override
    public Map<String, Object> getAttributeMap() {
        return attributes;
    }

    @Override
    public <T> T getAttribute(String name) {
        return (T) attributes.get(name);
    }

    @Override
    public void setAttribute(String name, Object value) {
        if (name == null)
            throw new NullPointerException("name");
        attributes.put(name, value);
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
    public <T> IHttpViewBuilder<T> getViewBuilder(Object obj) {
        IHttpViewBuilder<T> viewBuilder = (IHttpViewBuilder<T>) viewBuilderCache.get(obj);
        return viewBuilder;
    }

}