package net.bodz.bas.repr.req;

import java.io.Serializable;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.bodz.bas.c.java.io.FilePath;
import net.bodz.bas.std.rfc.mime.ContentType;
import net.bodz.bas.std.rfc.mime.ContentTypes;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.bas.t.variant.MutableVariantMap;

public class DefaultViewOfRequest
        extends AbstractHttpRequestProcessor
        implements IViewOfRequest, Serializable {

    private static final long serialVersionUID = 1L;

    private String viewName;
    private ContentType contentType = ContentTypes.text_html;
    private IVariantMap<String> parameters;

    public DefaultViewOfRequest() {
        parameters = new MutableVariantMap<String>(new HashMap<String, Object>());
    }

    @Override
    public String getViewName() {
        return viewName;
    }

    @Override
    public void setViewName(String viewName) {
        if (viewName == null)
            throw new NullPointerException("viewName");
        this.viewName = viewName;
    }

    @Override
    public ContentType getContentType() {
        return contentType;
    }

    @Override
    public void setContentType(ContentType contentType) {
        if (contentType == null)
            throw new NullPointerException("contentType");
        this.contentType = contentType;
    }

    @Override
    public IVariantMap<String> getParameters() {
        return parameters;
    }

    static boolean paramViewLong = true;
    static boolean paramView = true;

    // static boolean contentTypeAuto = true;

    @Override
    public boolean apply(HttpServletRequest request, HttpServletResponse response) {
        String viewName = null;
        if (viewName == null && paramViewLong)
            viewName = request.getParameter("view:");

        if (viewName == null && paramView)
            viewName = request.getParameter("v:");

        // if (viewName == null && contentTypeAuto)
        // request.getParameter("");
        this.viewName = viewName;

        // TODO Multiple content-types?
        ContentType contentType = null;
        if (contentType == null) {
            String accept = request.getHeader("Accept-Content-Type");
            if (accept == null)
                accept = request.getParameter("contentType:");
            if (accept != null)
                contentType = ContentType.forName(accept);
        }
        if (contentType == null) {
            String extension = FilePath.getExtension(request.getRequestURI());
            if (extension != null)
                contentType = ContentType.forExtension(extension);
        }
        if (contentType != null)
            setContentType(contentType);

        request.setAttribute(IViewOfRequest.ATTRIBUTE_KEY, this);
        return true;
    }

    @Override
    public String toString() {
        return viewName;
    }

}
