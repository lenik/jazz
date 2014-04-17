package net.bodz.bas.repr.viz;

import net.bodz.bas.potato.ref.IRefEntry;
import net.bodz.bas.repr.view.IViewStruct;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.t.order.IPriority;

/**
 * @see IViewStruct
 */
public interface IViewBuilder<T>
        extends IPriority {

    /**
     * Get the value type.
     *
     * Only sub-classes of this type can be built by this view builder.
     *
     * @return Non-<code>null</code> array of supported classes.
     */
    Class<?> getValueType();

    /**
     * Get supported features.
     *
     * @return Non-<code>null</code> array of supported feature names.
     */
    String[] getSupportedFeatures();

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
