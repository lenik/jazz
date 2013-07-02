package net.bodz.bas.repr.viz;

import net.bodz.bas.potato.ref.IRefEntry;

public interface IViewBuildStrategy {

    /**
     * TODO class/annotation/parameters...
     */
    <T> IViewBuilder<T> findViewBuilder(Class<? extends T> type);

    Object buildView(Object ctx, IRefEntry<?> entry, ViewBuildOption... options)
            throws ViewBuilderException;

}
