package net.bodz.bas.gui.viz;

import net.bodz.bas.c.primitive.Primitives;
import net.bodz.bas.c.type.TypePoMap;
import net.bodz.bas.gui.err.GUIException;
import net.bodz.bas.i18n.nls.II18nCapable;
import net.bodz.bas.potato.ref.IRefEntry;

public abstract class AbstractVisualization
        extends TypePoMap<IRenderer>
        implements II18nCapable  {

    private static final long serialVersionUID = 1L;

    /**
     * Render editable var
     * 
     * @throws GUIException
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
