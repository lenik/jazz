package net.bodz.bas.repr.viz;

import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.ui.dom1.IUiRef;

@IndexedType
public interface IViewBuilderFactory {

    /**
     * @return Non-<code>null</code> array of supported feature names.
     */
    String[] getSupportedFeatures();

    /**
     * @param features
     *            Required features.
     */
    <T> IViewBuilder<T> getViewBuilder(Class<? extends T> type, String... features);

    /**
     * @return The built widget/control handle.
     */
    Object buildView(Object ctx, IUiRef<?> ref)
            throws ViewBuilderException;

    /**
     * @return The built widget/control handle.
     */
    Object buildView(Object ctx, IUiRef<?> ref, IOptions options)
            throws ViewBuilderException;

}
