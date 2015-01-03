package net.bodz.bas.html.viz.builtin;

import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.dom.tag.HtmlInputTag;

public class Long_htm
        extends AbstractNumericForm_htm<Long> {

    public Long_htm() {
        super(Long.class);
    }

    @Override
    protected HtmlInputTag createInput(IHtmlTag out) {
        HtmlInputTag input = out.input().type("number");
        input.min("" + Long.MIN_VALUE);
        input.max("" + Long.MAX_VALUE);
        // 18 446 744 073 709 551 616
        input.maxlength("20");
        input.size("20");
        return input;
    }

}
