package net.bodz.swt.viz.form.vbo;

import org.eclipse.swt.SWTException;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import net.bodz.bas.gui.viz.ViewBuilderException;
import net.bodz.bas.potato.ref.IRefEntry;
import net.bodz.swt.viz.SwtRenderContext;
import net.bodz.swt.viz.SwtViewBuilder;
import net.bodz.swt.viz.MappedSwtVizStyleClass;

public class NumberVbo
        extends SwtViewBuilder {

    @Override
    public Control buildView(SwtRenderContext rc, IRefEntry<?> entry, MappedSwtVizStyleClass stylesheet, Composite parent, int style)
            throws ViewBuilderException, SWTException {
        // GUIVarMeta meta = var.getMeta();
        // if min/max then render in slider...
        return new StringVbo().buildView(rc, entry, stylesheet, parent, style);
    }

}
