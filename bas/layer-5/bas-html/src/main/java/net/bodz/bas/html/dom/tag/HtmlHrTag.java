package net.bodz.bas.html.dom.tag;

import net.bodz.bas.html.dom.IHtmlTag;

public class HtmlHrTag
        extends _HtmlHrTag<HtmlHrTag> {

    public HtmlHrTag(IHtmlTag parent, String tagName) {
        super(parent, tagName);
    }

    @Override
    public boolean isVoid() {
        return true;
    }

}
