package net.bodz.bas.repr.req;

import java.io.Serializable;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.bodz.bas.std.rfc.mime.ContentType;
import net.bodz.bas.std.rfc.mime.ContentTypes;
import net.bodz.bas.t.variant.IVariantLookupMap;
import net.bodz.bas.t.variant.VariantMap;

public class DefaultViewOfRequest
        extends AbstractHttpRequestProcessor
        implements IViewOfRequest, Serializable {

    private static final long serialVersionUID = 1L;

    private String viewName;
    private ContentType contentType = ContentTypes.text_html;
    private IVariantLookupMap<String> parameters;

    DefaultViewOfRequest() {
    }

    public DefaultViewOfRequest(String viewName, Map<String, Object> parameterMap) {
        this.viewName = viewName;
        this.parameters = new VariantMap<>(parameterMap);
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
    public IVariantLookupMap<String> getParameters() {
        return parameters;
    }

    static boolean paramViewLong = true;
    static boolean paramView = true;
    static boolean contentTypeAuto = true;

    @Override
    public void apply(HttpServletRequest request) {
        String viewName = null;
        if (viewName == null && paramViewLong)
            viewName = request.getParameter("view:");

        if (viewName == null && paramView)
            viewName = request.getParameter("v:");

        if (viewName == null && contentTypeAuto)
            request.getParameter("");

        // TODO Multiple content-types?
        // Content-type by browser.
        String acceptContentType = request.getHeader("Accept-Content-Type");
        if (acceptContentType != null) {
            ContentType contentType = ContentType.forName(acceptContentType);
            if (contentType != null)
                setContentType(contentType);
        }

        request.setAttribute(ATTRIBUTE_KEY, this);
    }

}
