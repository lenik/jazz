package net.bodz.bas.http.viz;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.repr.viz.IViewBuilder;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.rtx.IOptions;
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

    String getEncoding();

    /**
     * Starts a new frame chain.
     */
    boolean isOrigin(T value);

    /**
     * A view frame can contain child views.
     */
    boolean isFrame();

    // boolean isDirectory();

    void preview(IHttpViewContext ctx, IUiRef<T> ref, IOptions options);

    void buildHttpView(IHttpViewContext ctx, HttpServletResponse resp, IUiRef<T> ref)
            throws ViewBuilderException, IOException;

    void buildHttpView(IHttpViewContext ctx, HttpServletResponse resp, IUiRef<T> ref, IOptions options)
            throws ViewBuilderException, IOException;

}
