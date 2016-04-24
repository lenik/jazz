package net.bodz.bas.html.dom.tag;

import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.dom.MutableHtmlTag;

public class MutableSummary
        extends MutableHtmlTag<MutableSummary> {

    public MutableSummary(IHtmlTag parent) {
        super(parent, "summary");
    }

}
