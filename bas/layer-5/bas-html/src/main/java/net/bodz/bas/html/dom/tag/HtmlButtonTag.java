package net.bodz.bas.html.dom.tag;

import net.bodz.bas.html.dom.IHtmlTag;

public class HtmlButtonTag
        extends _HtmlButtonTag<HtmlButtonTag> {

    public HtmlButtonTag(IHtmlTag parent, String tagName) {
        super(parent, tagName);
    }

    public HtmlButtonTag dataToggle(Object val) {
        attr("data-toggle", val);
        return this;
    }
    
}
