package net.bodz.bas.ui;

public interface IRenderer {

    Object render(Object context, Var<?> var)
            throws RenderException;

}
