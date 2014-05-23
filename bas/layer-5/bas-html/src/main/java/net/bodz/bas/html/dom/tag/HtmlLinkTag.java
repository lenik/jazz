package net.bodz.bas.html.dom.tag;

import net.bodz.bas.html.dom.IHtmlTag;

public class HtmlLinkTag
        extends _HtmlLinkTag<HtmlLinkTag> {

    public HtmlLinkTag(IHtmlTag parent, String tagName) {
        super(parent, tagName);
    }

    public HtmlLinkTag css(String href) {
        if (href == null)
            throw new NullPointerException("href");
        rel("stylesheet");
        type("text/css");
        href(href);
        return this;
    }

}
