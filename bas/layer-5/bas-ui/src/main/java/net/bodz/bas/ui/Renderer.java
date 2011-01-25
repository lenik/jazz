package net.bodz.bas.ui;

public interface Renderer {

    Object render(Object context, Var<?> var)
            throws RenderException;

}
