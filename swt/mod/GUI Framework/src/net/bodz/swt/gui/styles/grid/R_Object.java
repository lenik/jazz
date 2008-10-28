package net.bodz.swt.gui.styles.grid;

import static net.bodz.swt.gui.util.SWTInject.styleFx;
import net.bodz.bas.gui.RenderException;
import net.bodz.bas.lang.err.NotImplementedException;
import net.bodz.swt.gui.GUIStruct;
import net.bodz.swt.gui.GUIVar;
import net.bodz.swt.gui.GUIStructs.GUIObjectStruct;
import net.bodz.swt.gui.styles.base._R_Object;

import org.eclipse.swt.SWTException;
import org.eclipse.swt.widgets.Composite;

public class R_Object extends _R_Object {

    protected final SWTGridStyle gridStyle;

    public R_Object(SWTGridStyle gridStyle) {
        super(gridStyle);
        this.gridStyle = gridStyle;
    }

    @Override
    protected Composite renderObject(GUIVar<?> var, Composite parent, int style)
            throws RenderException, SWTException {
        assert var != null;
        Object object = var.get();
        if (object != null) {
            GUIStruct objStruct = new GUIObjectStruct(object);
            return gridStyle.renderStruct(objStruct, parent,
                    styleFx(style, var));
        }
        throw new NotImplementedException("null obj");
    }

}
