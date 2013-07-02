package net.bodz.swt.viz.form.vbo;

import org.eclipse.swt.SWTException;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import net.bodz.bas.potato.ref.IRefEntry;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.swt.viz.ISwtControlStyleClass;
import net.bodz.swt.viz.SwtRenderContext;
import net.bodz.swt.viz.SwtViewBuilder;

public class NumberVbo
        extends SwtViewBuilder<Number> {

    @Override
    public Control buildView(SwtRenderContext rc, IRefEntry<Number> entry, ISwtControlStyleClass style,
            Composite parent, int styleInt)
            throws ViewBuilderException, SWTException {

        // GUIVarMeta meta = var.getMeta();
        // if min/max then render in slider...
        return new StringVbo()._buildView(rc, entry, style, parent, styleInt);
    }

}
