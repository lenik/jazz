package net.bodz.bas.html.viz.builtin;

import java.io.IOException;
import java.sql.Date;

import net.bodz.bas.c.java.util.Dates;
import net.bodz.bas.html.io.IHtmlOut;
import net.bodz.bas.html.io.tag.HtmlInput;
import net.bodz.bas.html.util.FieldDeclToHtml;
import net.bodz.bas.html.viz.IHtmlViewContext;
import net.bodz.bas.potato.ref.UiPropertyRef;
import net.bodz.bas.repr.form.IFormProperty;
import net.bodz.bas.repr.viz.ViewBuilderException;

public class SqlDate_htm
        extends AbstractFormInput_htm<Date> {

    public SqlDate_htm() {
        super(Date.class);
    }

    @Override
    public void buildHtmlView(IHtmlViewContext ctx, IHtmlOut out, UiPropertyRef<Date> ref, IFormProperty fieldDecl)
            throws ViewBuilderException, IOException {
        Date value = ref.get();

        HtmlInput input = out.input().class_("noprint");

        FieldDeclToHtml.apply(input, fieldDecl);

        input.type("date");
        if (value != null) {
            // The RFC 3339/ISO 8601 "wire format": YYYY-MM-DD.
            String str = Dates.ISO_LOCAL_DATE.format(value);
            input.value(str);
            out.span().class_("print").text(str);
        }
    }

}
