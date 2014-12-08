package net.bodz.bas.repr.viz;

import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.t.order.IPriority;
import net.bodz.bas.ui.dom1.IUiRef;

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
    Object buildView(Object ctx, IUiRef<T> ref)
            throws ViewBuilderException;

    /**
     * @return The built widget/control handle.
     */
    Object buildView(Object ctx, IUiRef<T> ref, IOptions options)
            throws ViewBuilderException;

}
