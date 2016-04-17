package net.bodz.bas.html.viz.builtin;

import java.io.IOException;

import net.bodz.bas.html.io.IHtmlOut;
import net.bodz.bas.html.io.tag.HtmlInput;
import net.bodz.bas.html.util.FieldDeclToHtml;
import net.bodz.bas.html.viz.IHtmlViewContext;
import net.bodz.bas.potato.ref.UiPropertyRef;
import net.bodz.bas.repr.form.IFieldDecl;
import net.bodz.bas.repr.viz.ViewBuilderException;

public class Boolean_htm
        extends AbstractFormInput_htm<Boolean> {

    public Boolean_htm() {
        super(Boolean.class);
    }

    @Override
    public void buildHtmlView(IHtmlViewContext ctx, IHtmlOut out, UiPropertyRef<Boolean> ref, IFieldDecl fieldDecl)
            throws ViewBuilderException, IOException {

        HtmlInput input = out.input().type("checkbox").class_("noprint");
        FieldDeclToHtml.apply(input, fieldDecl);

        Boolean value = ref.get();
        char sign = '☐';
        if (value != null) {
            if (value)
                input.checked("checked");
            sign = value ? '☑' : '☒';
        }
        out.span().class_("print").text(sign);
    }

}
