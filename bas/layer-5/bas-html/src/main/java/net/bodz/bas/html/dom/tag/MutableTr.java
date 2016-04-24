package net.bodz.bas.html.dom.tag;

import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.dom.tag.gen._MutableTr;

public class MutableTr
        extends _MutableTr<MutableTr> {

    public MutableTr(IHtmlTag parent) {
        super(parent);
    }

    public MutableTr valign(Object val) {
        if (val == null)
            throw new NullPointerException("val");
        attr("valign", val);
        return this;
    }

}
