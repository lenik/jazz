package net.bodz.bas.html.viz.builtin;

import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.dom.tag.HtmlInputTag;
import net.bodz.bas.potato.ref.UiPropertyRef;
import net.bodz.bas.repr.form.IFieldDecl;

public class Short_htm
        extends AbstractNumericForm_htm<Short> {

    public Short_htm() {
        super(Short.class);
    }

    @Override
    protected HtmlInputTag createInput(IHtmlTag out, UiPropertyRef<Short> ref, IFieldDecl fieldDecl) {
        HtmlInputTag input = out.input().type("number");
        input.min("" + Short.MIN_VALUE);
        input.max("" + Short.MAX_VALUE);
        input.maxlength("5");
        input.size("5");
        return input;
    }

}
