package net.bodz.swt.viz;

import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Widget;

import net.bodz.bas.repr.viz.IViewBuilder;
import net.bodz.bas.repr.viz.ViewBuildOption;
import net.bodz.bas.repr.viz.ViewBuilderException;

public interface ISwtViewBuilder<T>
        extends IViewBuilder<T> {

    Widget buildView(Control parent, ISwtGUIRefEntry<T> entry, ViewBuildOption... options)
            throws ViewBuilderException;

}
