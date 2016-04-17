package net.bodz.bas.html.dom.tag;

import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.dom.tag.gen._MutableHr;

public class MutableHr
        extends _MutableHr<MutableHr> {

    public MutableHr(IHtmlTag parent, String tagName) {
        super(parent, tagName);
    }

    @Override
    public boolean isTerm() {
        return true;
    }

}
