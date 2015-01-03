package net.bodz.bas.html.viz.builtin;

import java.io.IOException;
import java.util.Date;

import net.bodz.bas.c.java.util.Dates;
import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.dom.tag.HtmlInputTag;
import net.bodz.bas.html.viz.IHtmlViewContext;
import net.bodz.bas.potato.ref.UiPropertyRef;
import net.bodz.bas.repr.form.FieldDeclBuilder;
import net.bodz.bas.repr.form.IFieldDecl;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.rtx.IOptions;

public class Date_htm
        extends AbstractFormInput_htm<Date> {

    static FieldDeclBuilder fieldDeclBuilder = new FieldDeclBuilder();

    public Date_htm() {
        super(Date.class);
    }

    @Override
    public IHtmlTag buildHtmlView(IHtmlViewContext ctx, IHtmlTag out, UiPropertyRef<Date> ref, IFieldDecl fieldDecl,
            IOptions options)
            throws ViewBuilderException, IOException {
        Date value = ref.get();

        HtmlInputTag input = out.input();

        apply(input, fieldDecl, options);

        // When not supported, the browser defaults to the text input type.
        // input.type("datetime"); // deprecated
        input.type("date");
        if (value != null) {
            input.value(Dates.D10T8.format(value));
        }

        return out;
    }

}
