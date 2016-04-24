package net.bodz.bas.html.dom.tag;

import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.dom.tag.gen._MutableTd;

public class MutableTd
        extends _MutableTd<MutableTd> {

    public MutableTd(IHtmlTag parent) {
        super(parent);
    }

    public MutableTd align(Object val) {
        if (val == null)
            throw new NullPointerException("val");
        attr("align", val);
        return this;
    }

    public MutableTd valign(Object val) {
        if (val == null)
            throw new NullPointerException("val");
        attr("valign", val);
        return this;
    }

    public MutableTd width(Object val) {
        if (val == null)
            throw new NullPointerException("val");
        attr("width", val);
        return this;
    }

}
