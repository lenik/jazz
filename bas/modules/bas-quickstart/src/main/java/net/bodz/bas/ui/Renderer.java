package net.bodz.bas.ui;

import net.bodz.bas.lang.ref.Var;

public interface Renderer {

    Object render(Object context, Var<?> var) throws RenderException;

}
