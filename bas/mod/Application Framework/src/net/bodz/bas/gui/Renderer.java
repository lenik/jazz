package net.bodz.bas.gui;

import net.bodz.bas.lang.KeyObject;
import net.bodz.bas.lang.ref.Var;

public interface Renderer {

    KeyObject HINT = new KeyObject("HINT");

    Object render(Var<?> var) throws RenderException;

}
