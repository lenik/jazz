package net.bodz.bas.html.viz.builtin;

import java.io.IOException;

import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.dom.tag.HtmlInputTag;
import net.bodz.bas.html.util.FieldHtmlUtil;
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
    public void buildHtmlView(IHtmlViewContext ctx, IHtmlTag out, UiPropertyRef<Boolean> ref, IFieldDecl fieldDecl)
            throws ViewBuilderException, IOException {

        HtmlInputTag input = out.input().type("checkbox").class_("noprint");
        FieldHtmlUtil.apply(input, fieldDecl);

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
