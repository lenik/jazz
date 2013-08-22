package net.bodz.bas.repr.viz;

import net.bodz.bas.potato.ref.IRefEntry;

public interface IViewBuilder<T> {

    /**
     * @return The built widget/control handle.
     */
    Object buildView(Object ctx, IRefEntry<T> entry, ViewBuildOption... options)
            throws ViewBuilderException;

}
