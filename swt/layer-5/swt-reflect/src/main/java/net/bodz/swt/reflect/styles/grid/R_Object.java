package net.bodz.swt.reflect.styles.grid;

import org.eclipse.swt.SWTException;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Composite;

import net.bodz.bas.ui.RenderException;
import net.bodz.swt.reflect.SwtEntryMetadata;
import net.bodz.swt.reflect.GUIStruct;
import net.bodz.swt.reflect.GUIStructs.GUIObjectStruct;
import net.bodz.swt.reflect.SwtEntry;
import net.bodz.swt.reflect.GUIVars;
import net.bodz.swt.reflect.SWTRenderContext;
import net.bodz.swt.reflect.styles.base._R_Object;

public class R_Object
        extends _R_Object {

    protected final SWTGridStrategy strategy;

    public R_Object(SWTGridStrategy gridStyle) {
        this.strategy = gridStyle;
    }

    @Override
    protected Composite renderObject(final SWTRenderContext rc, SwtEntry<?> var, Composite parent, int style)
            throws RenderException, SWTException {
        assert var != null;
        Object object = var.get();
        if (object == null) {
            object = "(null)";
            SwtEntryMetadata hint = new SwtEntryMetadata();
            hint.color = new RGB(255, 0, 0);
            var = GUIVars.wrap(object, hint);
        }
        GUIStruct objStruct = new GUIObjectStruct(object);
        return strategy.renderStruct(rc, objStruct, parent, styleFx(style, var));
    }

}
