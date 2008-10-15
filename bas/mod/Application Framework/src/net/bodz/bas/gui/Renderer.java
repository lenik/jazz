package net.bodz.bas.gui;

import net.bodz.bas.lang.ref.Var;

public interface Renderer {

    Object render(Var<?> var) throws RenderException;

}
