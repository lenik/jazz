package net.bodz.lily.model.base.impl;

import javax.servlet.http.HttpServletRequest;

import net.bodz.bas.html.viz.AbstractHtmlViewBuilder;
import net.bodz.bas.html.viz.HtmlViewOptions;
import net.bodz.bas.std.rfc.mime.ContentType;
import net.bodz.lily.model.base.CoObjectIndex;

public abstract class AbstractCoObjectIndex_htm<T extends CoObjectIndex>
        extends AbstractHtmlViewBuilder<T> {

    public AbstractCoObjectIndex_htm(Class<?> valueClass) {
        super(valueClass);
    }

    @Override
    public ContentType getContentType(HttpServletRequest request, T value) {
        HtmlViewOptions viewOptions = value.getViewOptions();
        ContentType contentType = viewOptions.getContentType();
        if (contentType != null)
            return contentType;
        else
            return super.getContentType(request, value);
    }

    @Override
    public boolean isOrigin(T value) {
        HtmlViewOptions viewOptions = value.getViewOptions();
        Boolean origin = viewOptions.getOrigin();
        if (origin != null)
            return origin;
        else
            return super.isOrigin(value);
    }

}
