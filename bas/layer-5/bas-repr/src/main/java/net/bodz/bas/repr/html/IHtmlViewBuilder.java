package net.bodz.bas.repr.html;

import java.util.Collection;

import net.bodz.bas.potato.ref.IRefEntry;
import net.bodz.bas.repr.viz.IViewBuilder;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.rtx.IOptions;

public interface IHtmlViewBuilder<T>
        extends IViewBuilder<T> {

    boolean isOrigin(T value);

    Collection<String> getRequiredLibraries();

    Collection<String> getRequiredStylesheets();

    IHtmlOutputContext buildHtmlView(IHtmlOutputContext ctx, IRefEntry<T> entry)
            throws ViewBuilderException;

    IHtmlOutputContext buildHtmlView(IHtmlOutputContext ctx, IRefEntry<T> entry, IOptions options)
            throws ViewBuilderException;

}
