package net.bodz.swt.viz;

import org.eclipse.swt.widgets.Control;

import net.bodz.bas.gui.viz.IViewBuilder;
import net.bodz.bas.gui.viz.ViewBuildOption;
import net.bodz.bas.gui.viz.ViewBuilderException;
import net.bodz.bas.potato.ref.IRefEntry;

public interface ISwtViewBuilder
        extends IViewBuilder {

    Object buildView(Control parent, IRefEntry<?> entry, ViewBuildOption... options)
            throws ViewBuilderException;

}
