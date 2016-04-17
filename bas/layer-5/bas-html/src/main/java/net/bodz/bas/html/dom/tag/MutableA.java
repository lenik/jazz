package net.bodz.bas.html.dom.tag;

import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.dom.tag.gen._MutableA;

public class MutableA
        extends _MutableA<MutableA> {

    public MutableA(IHtmlTag parent, String tagName) {
        super(parent, tagName);
    }

    public MutableA href(String target, Object val) {
        super.href(val);
        target(target);
        return this;
    }

}
