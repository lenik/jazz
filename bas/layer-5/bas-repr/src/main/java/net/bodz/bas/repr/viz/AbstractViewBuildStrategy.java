package net.bodz.bas.repr.viz;

import net.bodz.bas.c.primitive.Primitives;
import net.bodz.bas.c.type.TypePoMap;
import net.bodz.bas.gui.err.GUIException;
import net.bodz.bas.i18n.nls.II18nCapable;
import net.bodz.bas.potato.ref.IRefEntry;

public abstract class AbstractViewBuildStrategy
        extends TypePoMap<IViewBuilder<?>>
        implements IViewBuildStrategy, II18nCapable {

    private static final long serialVersionUID = 1L;

    /**
     * Render editable var
     * 
     * @throws GUIException
     * @throws NullPointerException
     *             if obj is <code>null</code>.
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public Object buildView(Object ctx, IRefEntry<?> entry, ViewBuildOption... options)
            throws ViewBuilderException {
        IViewBuilder builder = findViewBuilder(entry);
        if (builder == null)
            throw new ViewBuilderException("Don't know how to render " + entry.getValueType());
        return builder.buildView(ctx, entry);
    }

    /**
     * @throws NullPointerException
     *             if var is null.
     * @return <code>null</code> if no matching renderer.
     */
    protected <T> IViewBuilder<T> findViewBuilder(IRefEntry<? extends T> entry) {
        if (entry == null)
            throw new NullPointerException("entry");
        Class<? extends T> type = (Class<T>) entry.getValueType();
        return findViewBuilder(type);
    }

    @Override
    public <T> IViewBuilder<T> findViewBuilder(Class<? extends T> type) {
        Class<?> usingType = floorKey(type);
        if (usingType == null) {
            if (type.isPrimitive()) {
                usingType = floorKey(Primitives.box(type));
                if (usingType == null)
                    return null;
            } else
                return null;
        }
        IViewBuilder<T> viewBuilder = (IViewBuilder<T>) get(usingType);
        return viewBuilder;
    }

}
