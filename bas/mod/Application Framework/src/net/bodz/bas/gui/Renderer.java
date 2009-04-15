package net.bodz.bas.gui;

import net.bodz.bas.lang.ref.Var;

public interface Renderer {

    Object render(Object context, Var<?> var) throws RenderException;

}
