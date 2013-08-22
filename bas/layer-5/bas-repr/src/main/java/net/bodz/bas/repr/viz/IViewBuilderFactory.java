package net.bodz.bas.repr.viz;

import net.bodz.bas.potato.ref.IRefEntry;
import net.bodz.bas.rtx.IOptions;

public interface IViewBuilderFactory {

    <T> IViewBuilder<T> getViewBuilder(Class<? extends T> type);

    /**
     * @return The built widget/control handle.
     */
    Object buildView(Object ctx, IRefEntry<?> entry)
            throws ViewBuilderException;

    /**
     * @return The built widget/control handle.
     */
    Object buildView(Object ctx, IRefEntry<?> entry, IOptions options)
            throws ViewBuilderException;

}
