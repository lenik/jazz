package net.bodz.bas.gui;

import java.util.Map;

import net.bodz.bas.lang.KeyObject;

public interface Renderer {

    KeyObject HINT = new KeyObject("HINT");

    Object render(Object obj, Map<?, ?> ctx) throws RenderException;

}
