package net.bodz.swt.viz.form.vbo;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.rtx.IOptions;
import net.bodz.swt.viz.AbstractSwtViewBuilder;
import net.bodz.swt.viz.ISwtControlStyleDeclaration;
import net.bodz.swt.viz.ISwtGUIRefEntry;

public class NumberVbo<number_t extends Number>
        extends AbstractSwtViewBuilder<number_t> {

    @Override
    public Widget buildView(Composite parent, ISwtGUIRefEntry<number_t> numEntry, int styleInt, IOptions options)
            throws ViewBuilderException {
        final ISwtControlStyleDeclaration styleDecl = numEntry.getStyle();

        // GUIVarMeta meta = var.getMeta();
        // if min/max then render in slider...
        return new StringVbo().buildView(parent, numEntry, styleInt, options);
    }

}
