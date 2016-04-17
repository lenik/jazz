package net.bodz.bas.repr.viz;

import net.bodz.bas.rtx.IAttributed;
import net.bodz.bas.rtx.IQueryable;
import net.bodz.bas.t.order.IPriority;
import net.bodz.bas.ui.dom1.IUiRef;

public interface IViewBuilder<T>
        extends IPriority, IAttributed {

    /**
     * Get the value type.
     * 
     * Only sub-classes of this type can be built by this view builder.
     * 
     * @return Non-<code>null</code> array of supported classes.
     */
    Class<?> getValueType();

    /**
     * @return The intermediate widget/control handle.
     */
    Object buildViewStart(IQueryable ctx, Object parent, IUiRef<T> ref)
            throws ViewBuilderException;

    /**
     * @param o
     *            The intermediate widget/control handle.
     * @return The build widget/control handle.
     */
    Object buildViewEnd(IQueryable ctx, Object parent, Object o, IUiRef<T> ref)
            throws ViewBuilderException;

}
