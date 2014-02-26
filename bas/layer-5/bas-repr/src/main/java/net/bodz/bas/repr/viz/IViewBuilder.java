package net.bodz.bas.repr.viz;

import net.bodz.bas.potato.ref.IRefEntry;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.t.order.IPriority;

public interface IViewBuilder<T>
        extends IPriority {

    /**
     * @return Non-<code>null</code> array of supported classes.
     */
    Class<?>[] getSupportedClasses();

    /**
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
