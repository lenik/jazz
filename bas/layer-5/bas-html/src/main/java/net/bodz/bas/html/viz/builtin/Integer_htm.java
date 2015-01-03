package net.bodz.bas.html.viz.builtin;

import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.dom.tag.HtmlInputTag;

public class Integer_htm
        extends AbstractNumericForm_htm<Integer> {

    public Integer_htm() {
        super(Integer.class);
    }

    @Override
    protected HtmlInputTag createInput(IHtmlTag out) {
        HtmlInputTag input = out.input().type("number");
        input.min("" + Integer.MIN_VALUE);
        input.max("" + Integer.MAX_VALUE);
        // 4 294 967 296
        input.maxlength("10");
        input.size("10");
        return input;
    }

}
