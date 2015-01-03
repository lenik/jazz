package net.bodz.bas.html.viz.builtin;

import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.dom.tag.HtmlInputTag;

public class Byte_htm
        extends AbstractNumericForm_htm<Byte> {

    public Byte_htm() {
        super(Byte.class);
    }

    @Override
    protected HtmlInputTag createInput(IHtmlTag out) {
        HtmlInputTag input = out.input().type("number");
        input.min("" + Byte.MIN_VALUE);
        input.max("" + Byte.MAX_VALUE);
        input.maxlength("3");
        input.size("3");
        return input;
    }

}
