package net.bodz.bas.repr.viz;

import net.bodz.bas.potato.ref.IRefEntry;
import net.bodz.bas.rtx.IOptions;

public abstract class AbstractViewBuilder<T>
        implements IViewBuilder<T> {

    @Override
    public final Object buildView(Object ctx, IRefEntry<T> entry)
            throws ViewBuilderException {
        return buildView(ctx, entry, IOptions.NULL);
    }

}
