package net.bodz.bas.html.dom.tag;

import net.bodz.bas.html.dom.IHtmlTag;

public class HtmlTdTag
        extends _HtmlTdTag<HtmlTdTag> {

    public HtmlTdTag(IHtmlTag parent, String tagName) {
        super(parent, tagName);
    }

    public HtmlTdTag align(String val) {
        if (val == null)
            throw new NullPointerException("val");
        attr("align", val);
        return this;
    }

    public HtmlTdTag valign(String val) {
        if (val == null)
            throw new NullPointerException("val");
        attr("valign", val);
        return this;
    }

    public HtmlTdTag width(String val) {
        if (val == null)
            throw new NullPointerException("val");
        attr("width", val);
        return this;
    }

}
