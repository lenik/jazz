package net.bodz.bas.html.dom.tag;

import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.dom.tag.gen._MutableDiv;

public class MutableDiv
        extends _MutableDiv<MutableDiv> {

    public MutableDiv(IHtmlTag parent) {
        super(parent);
    }

    public MutableDiv align(Object val) {
        if (val == null)
            throw new NullPointerException("val");
        attr("align", val);
        return this;
    }

}
