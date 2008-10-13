package net.bodz.bas.gui;

import java.util.Map;

import net.bodz.bas.types.TypeHierMap;

public abstract class Style extends TypeHierMap<Renderer> {

    private static final long serialVersionUID = 383209437177989123L;

    /**
     * @throws GUIException
     * @throws NullPointerException
     *             if obj is <code>null</code>.
     */
    public Object render(Object obj, Map<?, ?> ctx) throws GUIException {
        if (obj == null)
            throw new NullPointerException();
        Class<?> type = obj.getClass();
        Class<?> usingType = getParentKey(type);
        if (usingType == null)
            throw new GUIException("Don't know how to render " + type);
        Renderer renderer = get(usingType);
        assert renderer != null;
        return renderer.render(obj, ctx);
    }

}
