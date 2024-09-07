package net.bodz.lily.model.base.impl;

import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.html.viz.AbstractHtmlViewBuilder;
import net.bodz.bas.html.viz.HtmlViewOptions;
import net.bodz.bas.html.viz.IHtmlViewContext;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.std.rfc.mime.ContentType;
import net.bodz.bas.t.variant.VariantMaps;
import net.bodz.lily.model.base.CoObject;
import net.bodz.lily.model.base.CoObjectCriteriaBuilder;
import net.bodz.lily.model.base.CoObjectIndex;

import jakarta.servlet.http.HttpServletRequest;

public abstract class AbstractCoObjectIndex_htm<T extends CoObjectIndex<E>, E extends CoObject>
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

    protected static interface fn
            extends AbstractHtmlViewBuilder.fn {

        class co {

            public static <M extends CoObjectCriteriaBuilder> M initCriteriaBuilder(M mask, IHtmlViewContext ctx)
                    throws ViewBuilderException {
                HttpServletRequest request = ctx.getRequest();
                // HttpSession session = request.getSession();
                try {
                    mask.readObject(VariantMaps.fromRequest(request));
                } catch (LoaderException | ParseException e) {
                    throw new ViewBuilderException("Failed to decode mask: " + e.getMessage(), e);
                }
                return mask;
            }

        }

    }

}
