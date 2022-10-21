package net.bodz.bas.html.viz.builtin;

import net.bodz.bas.html.io.IHtmlOut;
import net.bodz.bas.html.io.tag.HtmlInput;
import net.bodz.bas.potato.ref.UiPropertyRef;
import net.bodz.bas.repr.form.IFormProperty;

public class Short_htm
        extends AbstractNumericForm_htm<Short> {

    public Short_htm() {
        super(Short.class);
    }

    @Override
    protected HtmlInput createScreenInput(IHtmlOut out, UiPropertyRef<Short> ref, IFormProperty fieldDecl) {
        HtmlInput input = out.input().type("number").class_("noprint");
        input.min("" + Short.MIN_VALUE);
        input.max("" + Short.MAX_VALUE);
        input.maxlength("5");
        input.size("5");
        return input;
    }

}
