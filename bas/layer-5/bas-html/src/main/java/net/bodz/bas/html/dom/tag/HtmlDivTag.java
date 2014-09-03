package net.bodz.bas.html.dom.tag;

import net.bodz.bas.html.dom.IHtmlTag;

public class HtmlDivTag
        extends _HtmlDivTag<HtmlDivTag> {

    public HtmlDivTag(IHtmlTag parent, String tagName) {
        super(parent, tagName);
    }

    public HtmlDivTag align(String val) {
        if (val == null)
            throw new NullPointerException("val");
        attr("align", val);
        return this;
    }

}
