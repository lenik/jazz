package net.bodz.swt.viz;

import org.eclipse.swt.widgets.Control;

import net.bodz.bas.potato.ref.IRefEntry;
import net.bodz.bas.repr.viz.IViewBuilder;
import net.bodz.bas.repr.viz.ViewBuildOption;
import net.bodz.bas.repr.viz.ViewBuilderException;

public interface ISwtViewBuilder
        extends IViewBuilder {

    Object buildView(Control parent, IRefEntry<?> entry, ViewBuildOption... options)
            throws ViewBuilderException;

}
