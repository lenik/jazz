package net.bodz.bas.repr.viz;

import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.potato.ref.IRefEntry;
import net.bodz.bas.rtx.IOptions;

@IndexedType
public interface IViewBuilderFactory {

    /**
     * @return Non-<code>null</code> array of supported feature names.
     */
    String[] getSupportedFeatures();

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
