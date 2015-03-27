package net.bodz.bas.html.dom.tag;

import net.bodz.bas.html.dom.IHtmlTag;

public class HtmlATag
        extends _HtmlATag<HtmlATag> {

    public HtmlATag(IHtmlTag parent, String tagName) {
        super(parent, tagName);
    }

    public HtmlATag href(String target, Object val) {
        super.href(val);
        target(target);
        return this;
    }

}
