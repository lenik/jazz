package net.bodz.bas.repr.viz;

import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.rtx.IQueryable;
import net.bodz.bas.ui.dom1.IUiRef;

@IndexedType
public interface IViewBuilderFactory {

    /**
     * @param features
     *            Required features.
     * @return Non-<code>null</code>. If multiple view builders matched, the preferred one is
     *         returned.
     */
    <T> ViewBuilderSet<T> getViewBuilders(Class<? extends T> type, String... features);

    /**
     * @param features
     *            Required features.
     * @return Non-<code>null</code>. If multiple view builders matched, the preferred one is
     *         returned.
     */
    <T> ViewBuilderSet<T> getViewBuilders(IUiRef<? extends T> ref, String... features);

    /**
     * @param features
     *            Required features.
     * @return If multiple view builders matched, the preferred one is returned.
     */
    <T> IViewBuilder<T> getViewBuilder(Class<? extends T> type, String... features);

    /**
     * @param features
     *            Required features.
     * @return If multiple view builders matched, the preferred one is returned.
     */
    <T> IViewBuilder<T> getViewBuilder(IUiRef<? extends T> ref, String... features);

    /**
     * @return The built widget/control handle.
     */
    Object buildView(IQueryable ctx, Object parent, IUiRef<?> ref)
            throws ViewBuilderException;

    /**
     * @return The built widget/control handle.
     */
    Object buildView(IQueryable ctx, Object parent, IUiRef<?> ref, IOptions options)
            throws ViewBuilderException;

}
