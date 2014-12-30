package net.bodz.bas.html.viz.builtin;

import java.io.IOException;

import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.dom.tag.HtmlInputTag;
import net.bodz.bas.html.viz.IHtmlViewContext;
import net.bodz.bas.repr.form.FieldDeclBuilder;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.typer.Typers;
import net.bodz.bas.typer.std.IFormatter;
import net.bodz.bas.ui.dom1.IUiRef;

public abstract class AbstractTextForm_htm<T>
        extends AbstractFormInput_htm<T> {

    static FieldDeclBuilder fieldDeclBuilder = new FieldDeclBuilder();

    public AbstractTextForm_htm(Class<T> valueClass) {
        super(valueClass);
    }

    @Override
    public IHtmlTag buildHtmlView(IHtmlViewContext ctx, IHtmlTag out, IUiRef<T> ref, IOptions options)
            throws ViewBuilderException, IOException {
        T value = ref.get();

        HtmlInputTag input = out.input();
        IHtmlTag tag = input;

        apply(input, ref);

        IFormatter<T> formatter = Typers.getTyper(ref.getValueType(), IFormatter.class);
        if (formatter == null)
            throw new IllegalUsageException("No formatter available for " + ref.getValueType());

        if (value != null) {
            String text = formatter.format(value/* ,options */);
            if (text != null)
                input.value(text);
        }

        return out;
    }

}
