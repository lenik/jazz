package net.bodz.bas.html.viz.builtin;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import net.bodz.bas.html.io.IHtmlOut;
import net.bodz.bas.html.io.tag.HtmlInput;
import net.bodz.bas.html.util.FieldDeclToHtml;
import net.bodz.bas.html.viz.IHtmlViewContext;
import net.bodz.bas.potato.ref.UiPropertyRef;
import net.bodz.bas.repr.form.IFormProperty;
import net.bodz.bas.repr.viz.ViewBuilderException;

public class ZonedDateTime_htm
        extends AbstractFormInput_htm<ZonedDateTime> {

    DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public ZonedDateTime_htm() {
        super(ZonedDateTime.class);
    }

    @Override
    public void buildHtmlView(IHtmlViewContext ctx, IHtmlOut out, UiPropertyRef<ZonedDateTime> ref,
            IFormProperty fieldDecl)
            throws ViewBuilderException, IOException {
        ZonedDateTime zonedDateTime = ref.get();

        HtmlInput input = out.input().class_("noprint");

        FieldDeclToHtml.apply(input, fieldDecl);

        // When not supported, the browser defaults to the text input type.
        // input.type("datetime"); // deprecated
        input.type("date");
        if (zonedDateTime != null) {
            String str = format.format(zonedDateTime);
            input.value(str);
            out.span().class_("print").text(str);
        }
    }

}
