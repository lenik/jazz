package net.bodz.bas.gui.viz;

import net.bodz.bas.c.type.TypePrMap;
import net.bodz.bas.gui.ia.UIException;
import net.bodz.bas.potato.ref.IRefEntry;
import net.bodz.bas.util.primitive.Primitives;

public abstract class IVisualization
        extends TypePrMap<IRenderer> {

    private static final long serialVersionUID = 1L;

    /**
     * Render editable var
     * 
     * @throws UIException
     * @throws NullPointerException
     *             if obj is <code>null</code>.
     */
    public Object render(Object context, IRefEntry<?> entry)
            throws RenderException {
        IRenderer renderer = findRenderer(entry);
        if (renderer == null)
            throw new RenderException("Don't know how to render " + entry.getValueType());
        return renderer.render(context, entry);
    }

    /**
     * @throws NullPointerException
     *             if var is null.
     * @return <code>null</code> if no matching renderer.
     */
    protected IRenderer findRenderer(IRefEntry<?> entry) {
        if (entry == null)
            throw new NullPointerException("entry");
        Class<?> type = entry.getValueType();
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
