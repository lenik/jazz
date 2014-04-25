package net.bodz.bas.html;

import java.io.IOException;

import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.potato.ref.IRefEntry;
import net.bodz.bas.repr.viz.IViewBuilder;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.std.rfc.mime.ContentType;

/**
 * HTML view builder interface.
 */
@IndexedType
public interface IHtmlViewBuilder<T>
        extends IViewBuilder<T> {

    String HTTP_HEADER = "http";
    String JAVASCRIPT = "js";
    String CSS = "css";

    ContentType getContentType(T value);

    /**
     * Starts a new frame chain.
     */
    boolean isOrigin(T value);

    /**
     * A view frame can contain child views.
     */
    boolean isFrame();

    void getRequirements(IRequirements requires);

    void buildTitle(StringBuilder buffer, T value);

    IHttpReprContext buildHtmlView(IHttpReprContext ctx, IRefEntry<T> entry)
            throws ViewBuilderException, IOException;

    IHttpReprContext buildHtmlView(IHttpReprContext ctx, IRefEntry<T> entry, IOptions options)
            throws ViewBuilderException, IOException;

    void buildHtmlViewTail(IHttpReprContext ctx, IRefEntry<T> entry)
            throws ViewBuilderException, IOException;

    void buildHtmlViewTail(IHttpReprContext ctx, IRefEntry<T> entry, IOptions options)
            throws ViewBuilderException, IOException;

}
