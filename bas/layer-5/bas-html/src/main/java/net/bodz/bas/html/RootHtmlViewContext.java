package net.bodz.bas.html;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.bodz.bas.html.dom.HtmlDoc;
import net.bodz.bas.html.dom.IHtmlTag;

public class RootHtmlViewContext
        extends AbstractHtmlViewContext
        implements IHtmlViewContext {

    private final HttpServletRequest request;
    private final HttpServletResponse response;

    private IHtmlMetaData metaData;
    private Map<String, Object> attributes;

    private HtmlDoc htmlDoc;
    private IHtmlTag outerTag;
    private Map<String, IHtmlTag> anchors;

    private Map<Object, IHtmlViewBuilder<?>> viewBuilderCache;

    public RootHtmlViewContext(HttpServletRequest request, HttpServletResponse response) {
        if (request == null)
            throw new NullPointerException("request");
        if (response == null)
            throw new NullPointerException("response");
        this.request = request;
        this.response = response;

        metaData = new HtmlMetaData();
        attributes = new HashMap<String, Object>();

        htmlDoc = new HtmlDoc();
        outerTag = htmlDoc.getRoot();
        anchors = new HashMap<>();

        viewBuilderCache = new IdentityHashMap<>();
    }

    @Override
    public IHtmlViewContext getRoot() {
        return this;
    }

    @Override
    public IHtmlViewContext getParent() {
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
    public IHtmlMetaData getMetaData() {
        return metaData;
    }

    @Override
    public Map<String, Object> getAttributeMap() {
        return attributes;
    }

    @Override
    public Object getAttribute(String name) {
        return attributes.get(name);
    }

    @Override
    public void setAttribute(String name, Object value) {
        if (name == null)
            throw new NullPointerException("name");
        attributes.put(name, value);
    }

    public HtmlDoc getHtmlDoc() {
        return htmlDoc;
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
    public IHtmlTag getAnchor(String name) {
        return anchors.get(name);
    }

    @Override
    public void setAnchor(String name, IHtmlTag anchor) {
        anchors.put(name, anchor);
    }

    @Override
    public <T> IHtmlViewBuilder<T> getViewBuilder(Object obj) {
        IHtmlViewBuilder<T> viewBuilder = (IHtmlViewBuilder<T>) viewBuilderCache.get(obj);
        return viewBuilder;
    }

}
