package net.bodz.bas.repr.html;

import net.bodz.bas.potato.ref.IRefEntry;
import net.bodz.bas.repr.viz.AbstractViewBuilder;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.rtx.IOptions;

public abstract class AbstractHtmlViewBuilder<T>
        extends AbstractViewBuilder<T>
        implements IHtmlViewBuilder<T> {

    @Override
    public boolean isOrigin(T value) {
        return false;
    }

    @Override
    public void getRequirements(IRequirements requires) {
    }

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

}
