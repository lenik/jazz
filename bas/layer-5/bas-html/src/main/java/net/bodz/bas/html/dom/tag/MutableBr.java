package net.bodz.bas.html.dom.tag;

import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.dom.tag.gen._MutableBr;

public class MutableBr
        extends _MutableBr<MutableBr> {

    public MutableBr(IHtmlTag parent) {
        super(parent);
    }

    @Override
    public boolean isTerm() {
        return true;
    }

}
