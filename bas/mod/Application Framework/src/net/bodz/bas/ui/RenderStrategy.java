package net.bodz.bas.ui;

import net.bodz.bas.lang.ref.Var;
import net.bodz.bas.nls.AppNLS;
import net.bodz.bas.types.TypeHierMap;
import net.bodz.bas.types.util.Types;

public abstract class RenderStrategy extends TypeHierMap<Renderer> {

    private static final long serialVersionUID = 383209437177989123L;

    /**
     * Render editable var
     * 
     * @throws UIException
     * @throws NullPointerException
     *             if obj is <code>null</code>.
     */
    public Object render(Object context, Var<?> var) throws RenderException {
        Renderer renderer = findRenderer(var);
        if (renderer == null)
            throw new RenderException(AppNLS.getString("RenderStrategy.unkRender") //$NON-NLS-1$
                    + var.getMeta().getType());
        return renderer.render(context, var);
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
        return findRenderer(type);
    }

    protected Renderer findRenderer(Class<?> type) {
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
