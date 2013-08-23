package net.bodz.swt.viz;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import net.bodz.bas.repr.viz.IViewBuilder;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.rtx.IOptions;

public interface ISwtViewBuilder<T>
        extends IViewBuilder<T> {

    Widget buildView(Composite parent, ISwtGUIRefEntry<T> entry, int styleInt)
            throws ViewBuilderException;

    Widget buildView(Composite parent, ISwtGUIRefEntry<T> entry, int styleInt, IOptions options)
            throws ViewBuilderException;

}
