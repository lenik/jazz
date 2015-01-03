package net.bodz.bas.html.viz.builtin;

import java.io.IOException;

import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.dom.tag.HtmlInputTag;
import net.bodz.bas.html.viz.IHtmlViewContext;
import net.bodz.bas.potato.ref.UiPropertyRef;
import net.bodz.bas.repr.form.IFieldDecl;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.rtx.IOptions;

public class Boolean_htm
        extends AbstractFormInput_htm<Boolean> {

    public Boolean_htm() {
        super(Boolean.class);
    }

    @Override
    public IHtmlTag buildHtmlView(IHtmlViewContext ctx, IHtmlTag out, UiPropertyRef<Boolean> ref, IFieldDecl fieldDecl,
            IOptions options)
            throws ViewBuilderException, IOException {

        HtmlInputTag input = out.input().type("checkbox");
        apply(input, fieldDecl, options);

        Boolean value = ref.get();
        if (value != null && value)
            input.checked("checked");

        return out;
    }

}
