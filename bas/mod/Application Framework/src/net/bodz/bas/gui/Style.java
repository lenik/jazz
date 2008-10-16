package net.bodz.bas.gui;

import net.bodz.bas.lang.ref.Var;
import net.bodz.bas.types.TypeHierMap;
import net.bodz.bas.types.util.Types;

public abstract class Style extends TypeHierMap<Renderer> {

    private static final long serialVersionUID = 383209437177989123L;

    /**
     * @throws GUIException
     * @throws NullPointerException
     *             if obj is <code>null</code>.
     */
    public Object render(Var<?> var) throws RenderException {
        Renderer renderer = findRenderer(var);
        if (renderer == null)
            throw new RenderException("Don't know how to render "
                    + var.getMeta().getType());
        return renderer.render(var);
    }

    /**
     * @throws NullPointerException
     *             if var is null.
     * @return <code>null</code> if no matching renderer.
     */
    protected Renderer findRenderer(Var<?> var) {
        if (var == null)
            throw new NullPointerException();
        Class<?> type = var.getMeta().getType();
        Class<?> usingType = floorKey(type);
        if (usingType == null) {
            if (type.isPrimitive()) {
                usingType = floorKey(Types.box(type));
                if (usingType == null)
                    return null;
            } else
                return null;
        }
        return get(usingType);
    }

}
