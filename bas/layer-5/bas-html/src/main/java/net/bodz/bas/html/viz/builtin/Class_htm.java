package net.bodz.bas.html.viz.builtin;

import java.io.IOException;

import net.bodz.bas.html.io.IHtmlOut;
import net.bodz.bas.html.io.tag.HtmlInput;
import net.bodz.bas.html.util.FieldDeclToHtml;
import net.bodz.bas.html.viz.IHtmlViewContext;
import net.bodz.bas.potato.ref.UiPropertyRef;
import net.bodz.bas.repr.form.IFormProperty;
import net.bodz.bas.repr.viz.ViewBuilderException;

public class Class_htm
        extends AbstractFormInput_htm<Class<?>> {

    public Class_htm() {
        super(Class.class);
    }

    @Override
    public void buildHtmlView(IHtmlViewContext ctx, IHtmlOut out, UiPropertyRef<Class<?>> ref, IFormProperty fieldDecl)
            throws ViewBuilderException, IOException {

        HtmlInput input = out.input().type("text").class_("noprint");
        FieldDeclToHtml.apply(input, fieldDecl);

        Class<?> value = ref.get();
        String str = value == null ? null : value.toString();
        if (value != null)
            input.value(str);

        out.span().class_("print").text(str);
    }

}
