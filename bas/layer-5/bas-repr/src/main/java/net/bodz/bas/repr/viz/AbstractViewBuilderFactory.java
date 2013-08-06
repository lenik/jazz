package net.bodz.bas.repr.viz;

import net.bodz.bas.c.primitive.Primitives;
import net.bodz.bas.c.type.TypePoMap;
import net.bodz.bas.i18n.nls.II18nCapable;
import net.bodz.bas.potato.ref.IRefEntry;

public abstract class AbstractViewBuilderFactory
        extends TypePoMap<IViewBuilder<?>>
        implements IViewBuilderFactory, II18nCapable {

    private static final long serialVersionUID = 1L;

    /**
     * @throws NullPointerException
     *             if var is null.
     * @return <code>null</code> if no matching renderer.
     */
    protected <T> IViewBuilder<T> getViewBuilder(IRefEntry<? extends T> entry) {
        if (entry == null)
            throw new NullPointerException("entry");
        Class<? extends T> type = (Class<T>) entry.getValueType();
        return getViewBuilder(type);
    }

    @Override
    public <T> IViewBuilder<T> getViewBuilder(Class<? extends T> type) {
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
