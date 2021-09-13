package net.bodz.bas.repr.viz.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.repr.viz.IViewBuilder;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.servlet.IHttpViewContext;
import net.bodz.bas.std.rfc.mime.ContentType;
import net.bodz.bas.ui.dom1.IUiRef;

/**
 * HTML view builder interface.
 */
@IndexedType
public interface IHttpViewBuilder<T>
        extends IViewBuilder<T> {

    HttpViewBuilderFamily getFamily();

    ContentType getContentType(HttpServletRequest request, T value);

    String getEncoding(T value);

    /**
     * Starts a new frame chain.
     */
    boolean isOrigin(T value);

    /**
     * A view frame can contain child views.
     */
    boolean isFrame();

    // boolean isDirectory();

    void precompile(IHttpViewContext ctx, IUiRef<T> ref);

    /**
     * @return The intermediate widget/control handle.
     */
    Object buildHttpViewStart(IHttpViewContext ctx, HttpServletResponse resp, IUiRef<T> ref)
            throws ViewBuilderException, IOException;

    /**
     * @param o
     *            The intermediate widget/control handle.
     */
    void buildHttpViewEnd(IHttpViewContext ctx, HttpServletResponse resp, Object o, IUiRef<T> ref)
            throws ViewBuilderException, IOException;

}
