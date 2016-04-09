package net.bodz.bas.html.viz.builtin;

import java.io.IOException;
import java.util.Date;

import net.bodz.bas.c.java.util.Dates;
import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.dom.tag.HtmlInputTag;
import net.bodz.bas.html.util.FieldHtmlUtil;
import net.bodz.bas.html.viz.IHtmlViewContext;
import net.bodz.bas.potato.ref.UiPropertyRef;
import net.bodz.bas.repr.form.FieldDeclBuilder;
import net.bodz.bas.repr.form.IFieldDecl;
import net.bodz.bas.repr.viz.ViewBuilderException;

public class Date_htm
        extends AbstractFormInput_htm<Date> {

    static FieldDeclBuilder fieldDeclBuilder = new FieldDeclBuilder();

    public Date_htm() {
        super(Date.class);
    }

    @Override
    public void buildHtmlView(IHtmlViewContext ctx, IHtmlTag out, UiPropertyRef<Date> ref, IFieldDecl fieldDecl)
            throws ViewBuilderException, IOException {
        Date value = ref.get();

        HtmlInputTag input = out.input().class_("noprint");

        FieldHtmlUtil.apply(input, fieldDecl);

        // When not supported, the browser defaults to the text input type.
        // input.type("datetime"); // deprecated
        input.type("date");
        if (value != null) {
            String str = Dates.YYYY_MM_DD.format(value);
            input.value(str);
            out.span().class_("print").text(str);
        }
    }

}
