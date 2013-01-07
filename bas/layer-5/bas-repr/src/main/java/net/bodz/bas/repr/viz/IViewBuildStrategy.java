package net.bodz.bas.repr.viz;

import net.bodz.bas.potato.ref.IRefEntry;

public interface IViewBuildStrategy {

    IViewBuilder findViewBuilder(Class<?> type);

    Object buildView(Object ctx, IRefEntry<?> entry, ViewBuildOption... options)
            throws ViewBuilderException;

}
