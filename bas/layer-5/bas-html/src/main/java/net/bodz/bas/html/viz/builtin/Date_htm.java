package net.bodz.bas.html.viz.builtin;

import java.io.IOException;
import java.util.Date;

import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.dom.tag.HtmlInputTag;
import net.bodz.bas.html.viz.IHtmlViewContext;
import net.bodz.bas.repr.form.FieldDeclBuilder;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.ui.dom1.IUiRef;

public class Date_htm
        extends AbstractFormInput_htm<Date> {

    static FieldDeclBuilder fieldDeclBuilder = new FieldDeclBuilder();

    public Date_htm() {
        super(Date.class);
    }

    @Override
    public IHtmlTag buildHtmlView(IHtmlViewContext ctx, IHtmlTag out, IUiRef<Date> ref, IOptions options)
            throws ViewBuilderException, IOException {
        Date value = ref.get();

        HtmlInputTag input = out.input();

        apply(input, ref);

        input.type("date");
        if (value != null) {
            input.value(value.toString());
        }

        return out;
    }

}
