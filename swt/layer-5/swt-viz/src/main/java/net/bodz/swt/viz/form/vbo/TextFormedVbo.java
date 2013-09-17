package net.bodz.swt.viz.form.vbo;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.typer.std.IFormatter;
import net.bodz.bas.typer.std.IParser;
import net.bodz.swt.viz.AbstractSwtViewBuilder;
import net.bodz.swt.viz.ISwtGUIRefEntry;

public class TextFormedVbo<T>
        extends AbstractSwtViewBuilder<T> {

    @Override
    public Widget buildView(Composite parent, ISwtGUIRefEntry<T> entry, int styleInt, IOptions options)
            throws ViewBuilderException {

        IParser<T> parser = entry.query(IParser.class);
        IFormatter<T> formatter = entry.query(IFormatter.class);

        ISwtGUIRefEntry<String> textFormed = new TextFormedSwtGUIRefEntry<>(entry, parser, formatter);

        return new StringVbo().buildView(parent, textFormed, styleInt, options);
    }

}
