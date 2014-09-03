package net.bodz.bas.html.dom.tag;

import net.bodz.bas.html.dom.IHtmlTag;

public class HtmlTrTag
        extends _HtmlTrTag<HtmlTrTag> {

    public HtmlTrTag(IHtmlTag parent, String tagName) {
        super(parent, tagName);
    }

    public HtmlTrTag valign(String val) {
        if (val == null)
            throw new NullPointerException("val");
        attr("valign", val);
        return this;
    }

}
