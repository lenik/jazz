package net.bodz.bas.html.dom.tag;

import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.dom.tag.gen._MutableButton;

public class MutableButton
        extends _MutableButton<MutableButton> {

    public MutableButton(IHtmlTag parent, String tagName) {
        super(parent, tagName);
    }

    public MutableButton dataToggle(Object val) {
        attr("data-toggle", val);
        return this;
    }

}
