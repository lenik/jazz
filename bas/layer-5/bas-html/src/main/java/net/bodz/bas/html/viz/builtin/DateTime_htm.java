package net.bodz.bas.html.viz.builtin;

import java.io.IOException;

import org.joda.time.DateTime;

import net.bodz.bas.html.io.IHtmlOut;
import net.bodz.bas.html.io.tag.HtmlInput;
import net.bodz.bas.html.util.FieldDeclToHtml;
import net.bodz.bas.html.viz.IHtmlViewContext;
import net.bodz.bas.potato.ref.UiPropertyRef;
import net.bodz.bas.repr.form.IFormProperty;
import net.bodz.bas.repr.viz.ViewBuilderException;

public class DateTime_htm
        extends AbstractFormInput_htm<DateTime> {

    public DateTime_htm() {
        super(DateTime.class);
    }

    @Override
    public void buildHtmlView(IHtmlViewContext ctx, IHtmlOut out, UiPropertyRef<DateTime> ref, IFormProperty fieldDecl)
            throws ViewBuilderException, IOException {
        DateTime dateTime = ref.get();

        HtmlInput input = out.input().class_("noprint");

        FieldDeclToHtml.apply(input, fieldDecl);

        // When not supported, the browser defaults to the text input type.
        // input.type("datetime"); // deprecated
        input.type("date");
        if (dateTime != null) {
            String str = dateTime.toString("yyyy-MM-dd");
            input.value(str);
            out.span().class_("print").text(str);
        }
    }

}
