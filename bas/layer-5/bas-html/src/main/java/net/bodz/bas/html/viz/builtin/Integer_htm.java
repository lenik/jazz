package net.bodz.bas.html.viz.builtin;

import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.dom.tag.HtmlInputTag;
import net.bodz.bas.potato.ref.UiPropertyRef;
import net.bodz.bas.repr.form.IFieldDecl;

public class Integer_htm
        extends AbstractNumericForm_htm<Integer> {

    public Integer_htm() {
        super(Integer.class);
    }

    @Override
    protected HtmlInputTag createScreenInput(IHtmlTag out, UiPropertyRef<Integer> ref, IFieldDecl fieldDecl) {
        HtmlInputTag input = out.input().type("number").class_("noprint");
        input.min("" + Integer.MIN_VALUE);
        input.max("" + Integer.MAX_VALUE);
        // 4 294 967 296
        input.maxlength("10");
        input.size("10");
        return input;
    }

}
