package net.bodz.swt.gui.styles.grid;

import static net.bodz.swt.gui.util.SWTInject.styleFx;
import net.bodz.bas.lang.err.NotImplementedException;
import net.bodz.bas.ui.RenderException;
import net.bodz.swt.gui.GUIStruct;
import net.bodz.swt.gui.GUIVar;
import net.bodz.swt.gui.SWTRenderContext;
import net.bodz.swt.gui.GUIStructs.GUIObjectStruct;
import net.bodz.swt.gui.styles.base._R_Object;

import org.eclipse.swt.SWTException;
import org.eclipse.swt.widgets.Composite;

public class R_Object extends _R_Object {

    protected final SWTGridStrategy strategy;

    public R_Object(SWTGridStrategy gridStyle) {
        this.strategy = gridStyle;
    }

    @Override
    protected Composite renderObject(final SWTRenderContext rc, GUIVar<?> var,
            Composite parent, int style) throws RenderException, SWTException {
        assert var != null;
        Object object = var.get();
        if (object == null)
            throw new NotImplementedException("null obj: " + var); //$NON-NLS-1$
        GUIStruct objStruct = new GUIObjectStruct(object);
        return strategy
                .renderStruct(rc, objStruct, parent, styleFx(style, var));
    }

}
