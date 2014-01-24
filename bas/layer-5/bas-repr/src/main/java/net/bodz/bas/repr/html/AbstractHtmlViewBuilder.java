package net.bodz.bas.repr.html;

import java.util.Collection;
import java.util.Collections;

import net.bodz.bas.potato.ref.IRefEntry;
import net.bodz.bas.repr.viz.AbstractViewBuilder;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.rtx.IOptions;

public abstract class AbstractHtmlViewBuilder<T>
        extends AbstractViewBuilder<T>
        implements IHtmlViewBuilder<T> {

    @Override
    public final Object buildView(Object _ctx, IRefEntry<T> entry, IOptions options)
            throws ViewBuilderException {
        IHtmlOutputContext ctx = (IHtmlOutputContext) _ctx;
        return buildHtmlView(ctx, entry, options);
    }

    @Override
    public final IHtmlOutputContext buildHtmlView(IHtmlOutputContext ctx, IRefEntry<T> entry)
            throws ViewBuilderException {
        return buildHtmlView(ctx, entry, IOptions.NULL);
    }

    @Override
    public boolean isOrigin(T value) {
        return false;
    }

    @Override
    public Collection<String> getRequiredLibraries() {
        return Collections.emptySet();
    }

    @Override
    public Collection<String> getRequiredStylesheets() {
        return Collections.emptySet();
    }

}