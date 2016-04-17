package net.bodz.bas.html.viz.builtin;

import net.bodz.bas.html.io.IHtmlOut;
import net.bodz.bas.html.io.tag.HtmlInput;
import net.bodz.bas.potato.ref.UiPropertyRef;
import net.bodz.bas.repr.form.IFieldDecl;

public class Byte_htm
        extends AbstractNumericForm_htm<Byte> {

    public Byte_htm() {
        super(Byte.class);
    }

    @Override
    protected HtmlInput createScreenInput(IHtmlOut out, UiPropertyRef<Byte> ref, IFieldDecl fieldDecl) {
        HtmlInput input = out.input().type("number").class_("noprint");
        input.min("" + Byte.MIN_VALUE);
        input.max("" + Byte.MAX_VALUE);
        input.maxlength("3");
        input.size("3");
        return input;
    }

}
