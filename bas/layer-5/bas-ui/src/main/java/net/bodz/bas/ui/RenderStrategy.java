package net.bodz.bas.ui;

import net.bodz.bas.c.type.TypePrMap;
import net.bodz.bas.util.primitive.Primitives;

public abstract class RenderStrategy
        extends TypePrMap<IRenderer> {

    private static final long serialVersionUID = 383209437177989123L;

    /**
     * Render editable var
     * 
     * @throws UIException
     * @throws NullPointerException
     *             if obj is <code>null</code>.
     */
    public Object render(Object context, Var<?> var)
            throws RenderException {
        IRenderer renderer = findRenderer(var);
        if (renderer == null)
            throw new RenderException("Don\'t know how to render " + var.getType());
        return renderer.render(context, var);
    }

    /**
     * @throws NullPointerException
     *             if var is null.
     * @return <code>null</code> if no matching renderer.
     */
    protected IRenderer findRenderer(Var<?> var) {
        if (var == null)
            throw new NullPointerException();
        Class<?> type = var.getType();
        return findRenderer(type);
    }

    protected IRenderer findRenderer(Class<?> type) {
        Class<?> usingType = floorKey(type);
        if (usingType == null) {
            if (type.isPrimitive()) {
                usingType = floorKey(Primitives.box(type));
                if (usingType == null)
                    return null;
            } else
                return null;
        }
        return get(usingType);
    }

}
