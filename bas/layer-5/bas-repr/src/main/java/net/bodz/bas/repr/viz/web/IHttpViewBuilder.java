package net.bodz.bas.repr.viz.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.repr.viz.IViewBuilder;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.rtx.IQueryable;
import net.bodz.bas.servlet.IHttpViewContext;
import net.bodz.bas.std.rfc.mime.ContentType;
import net.bodz.bas.ui.dom1.IUiRef;

/**
 * HTML view builder interface.
 */
@IndexedType
public interface IHttpViewBuilder<T>
        extends
            IViewBuilder<T> {

    default HttpViewBuilderFamily getFamily() {
        return HttpViewBuilderFamily.GENERAL;
    }

    ContentType getContentType(HttpServletRequest request, T value);

    default String getEncoding(T value) {
        return "utf-8";
    }

    /**
     * Starts a new frame chain.
     */
    default boolean isOrigin(T value) {
        return true;
    }

    /**
     * A view frame can contain child views.
     */
    default boolean isFrame() {
        return false;
    }

    // boolean isDirectory();

    default void precompile(IHttpViewContext ctx, IUiRef<T> ref) {
    }

    @Override
    default Object buildViewStart(IQueryable _ctx, Object parent, IUiRef<T> ref)
            throws ViewBuilderException {
        IHttpViewContext ctx = (IHttpViewContext) _ctx;
        HttpServletResponse resp = ctx.getResponse();
        try {
            buildHttpViewStart(ctx, resp, ref);
        } catch (IOException e) {
            throw new ViewBuilderException(e.getMessage(), e);
        }
        return parent;
    }

    /**
     * @return The intermediate widget/control handle.
     */
    Object buildHttpViewStart(IHttpViewContext ctx, HttpServletResponse resp, IUiRef<T> ref)
            throws ViewBuilderException, IOException;

    /**
     * @param o
     *            The intermediate widget/control handle.
     */
    default void buildHttpViewEnd(IHttpViewContext ctx, HttpServletResponse resp, Object o, IUiRef<T> ref)
            throws ViewBuilderException, IOException {
    }

}
