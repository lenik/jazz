package net.bodz.bas.html.dom.tag;

import net.bodz.bas.html.dom.IHtmlTag;

public class HtmlInputTag
        extends _HtmlInputTag<HtmlInputTag> {

    public HtmlInputTag(IHtmlTag parent, String tagName) {
        super(parent, tagName);
    }

    public HtmlLabelTag idLabel(String id) {
        id(id);
        return label().for_(id);
    }

}
