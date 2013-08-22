package net.bodz.bas.repr.viz;

import net.bodz.bas.potato.ref.IRefEntry;
import net.bodz.bas.rtx.IOptions;

public interface IViewBuilder<T> {

    /**
     * @return The built widget/control handle.
     */
    Object buildView(Object ctx, IRefEntry<T> entry)
            throws ViewBuilderException;

    /**
     * @return The built widget/control handle.
     */
    Object buildView(Object ctx, IRefEntry<T> entry, IOptions options)
            throws ViewBuilderException;

}
