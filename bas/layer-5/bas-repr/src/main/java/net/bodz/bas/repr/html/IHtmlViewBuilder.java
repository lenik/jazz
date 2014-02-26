package net.bodz.bas.repr.html;

import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.potato.ref.IRefEntry;
import net.bodz.bas.repr.viz.IViewBuilder;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.rtx.IOptions;

@IndexedType
public interface IHtmlViewBuilder<T>
        extends IViewBuilder<T> {

    String HTTP_HEADER = "http";
    String JAVASCRIPT = "js";
    String CSS = "css";

    boolean isOrigin(T value);

    void getRequirements(IRequirements requires);

    IHtmlOutputContext buildHtmlView(IHtmlOutputContext ctx, IRefEntry<T> entry)
            throws ViewBuilderException;

    IHtmlOutputContext buildHtmlView(IHtmlOutputContext ctx, IRefEntry<T> entry, IOptions options)
            throws ViewBuilderException;

}
