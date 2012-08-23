package net.bodz.swt.viz.style.grid;

import org.eclipse.swt.SWTException;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Composite;

import net.bodz.bas.gui.viz.RenderException;
import net.bodz.swt.viz.SWTRenderContext;
import net.bodz.swt.viz.core.GUIStruct;
import net.bodz.swt.viz.core.GUIVars;
import net.bodz.swt.viz.core.SwtEntry;
import net.bodz.swt.viz.core.SwtEntryMetadata;
import net.bodz.swt.viz.core.GUIStructs.GUIObjectStruct;
import net.bodz.swt.viz.style._R_Object;

public class R_Object
        extends _R_Object {

    protected final GridVisualization strategy;

    public R_Object(GridVisualization gridStyle) {
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
