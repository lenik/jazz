package net.bodz.bas.html;

import java.io.IOException;

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
public interface IHtmlViewBuilder<T>
        extends IViewBuilder<T> {

    ContentType getContentType(T value);

    /**
     * Starts a new frame chain.
     */
    boolean isOrigin(T value);

    /**
     * A view frame can contain child views.
     */
    boolean isFrame();

    // boolean isDirectory();

    void preview(IHtmlViewContext ctx, IUiRef<T> ref, IOptions options);

    IHtmlViewContext buildHtmlView(IHtmlViewContext ctx, IUiRef<T> ref)
            throws ViewBuilderException, IOException;

    IHtmlViewContext buildHtmlView(IHtmlViewContext ctx, IUiRef<T> ref, IOptions options)
            throws ViewBuilderException, IOException;

    void buildHtmlViewTail(IHtmlViewContext ctx, IUiRef<T> ref)
            throws ViewBuilderException, IOException;

    void buildHtmlViewTail(IHtmlViewContext ctx, IUiRef<T> ref, IOptions options)
            throws ViewBuilderException, IOException;

}
