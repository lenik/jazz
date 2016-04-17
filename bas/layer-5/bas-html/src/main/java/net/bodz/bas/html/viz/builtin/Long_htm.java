package net.bodz.bas.html.viz.builtin;

import net.bodz.bas.html.io.IHtmlOut;
import net.bodz.bas.html.io.tag.HtmlInput;
import net.bodz.bas.potato.ref.UiPropertyRef;
import net.bodz.bas.repr.form.IFieldDecl;

public class Long_htm
        extends AbstractNumericForm_htm<Long> {

    public Long_htm() {
        super(Long.class);
    }

    @Override
    protected HtmlInput createScreenInput(IHtmlOut out, UiPropertyRef<Long> ref, IFieldDecl fieldDecl) {
        HtmlInput input = out.input().type("number").class_("noprint");
        input.min("" + Long.MIN_VALUE);
        input.max("" + Long.MAX_VALUE);
        // 18 446 744 073 709 551 616
        input.maxlength("20");
        input.size("20");
        return input;
    }

}
