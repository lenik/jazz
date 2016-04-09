package net.bodz.bas.repr.viz;

import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.rtx.IQueryable;
import net.bodz.bas.ui.dom1.IUiRef;

@IndexedType
public interface IViewBuilderFactory {

    /**
     * @param tags
     *            Preferred tags.
     * @return Non-<code>null</code>. If multiple view builders matched, the preferred one is
     *         returned.
     */
    <T> ViewBuilderSet<T> getViewBuilders(Class<? extends T> type, String... tags);

    /**
     * @param tags
     *            Preferred tags.
     * @return Non-<code>null</code>. If multiple view builders matched, the preferred one is
     *         returned.
     */
    <T> ViewBuilderSet<T> getViewBuilders(IUiRef<? extends T> ref, String... tags);

    /**
     * @param tags
     *            Preferred tags.
     * @return If multiple view builders matched, the preferred one is returned.
     */
    <T> IViewBuilder<T> getViewBuilder(Class<? extends T> type, String... tags);

    /**
     * @param tags
     *            Preferred tags.
     * @return If multiple view builders matched, the preferred one is returned.
     */
    <T> IViewBuilder<T> getViewBuilder(IUiRef<? extends T> ref, String... tags);

    /**
     * @return The built widget/control handle.
     */
    Object buildView(IQueryable ctx, Object parent, IUiRef<?> ref)
            throws ViewBuilderException;

}
