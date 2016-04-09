package net.bodz.bas.html.viz.builtin;

import java.io.IOException;

import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.dom.tag.HtmlInputTag;
import net.bodz.bas.html.util.FieldHtmlUtil;
import net.bodz.bas.html.viz.IHtmlViewContext;
import net.bodz.bas.potato.ref.UiPropertyRef;
import net.bodz.bas.repr.form.IFieldDecl;
import net.bodz.bas.repr.viz.ViewBuilderException;

public class Class_htm
        extends AbstractFormInput_htm<Class<?>> {

    public Class_htm() {
        super(Class.class);
    }

    @Override
    public void buildHtmlView(IHtmlViewContext ctx, IHtmlTag out, UiPropertyRef<Class<?>> ref, IFieldDecl fieldDecl)
            throws ViewBuilderException, IOException {

        HtmlInputTag input = out.input().type("text").class_("noprint");
        FieldHtmlUtil.apply(input, fieldDecl);

        Class<?> value = ref.get();
        String str = value.toString();
        if (value != null)
            input.value(str);

        out.span().class_("print").text(str);
    }

}
