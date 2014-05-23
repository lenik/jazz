package net.bodz.bas.html.dom.tag;

import net.bodz.bas.html.dom.IHtmlTag;

public class HtmlScriptTag
        extends _HtmlScriptTag<HtmlScriptTag> {

    public HtmlScriptTag(IHtmlTag parent, String tagName) {
        super(parent, tagName);
    }

    public HtmlScriptTag javascript(String src) {
        type("text/javascript");
        text(src);
        return this;
    }

    public HtmlScriptTag javascriptSrc(String href) {
        if (href == null)
            throw new NullPointerException("href");
        type("text/javascript"); // This is also the default.
        src(href);
        return this;
    }

    public HtmlScriptTag vbscript(String src) {
        type("text/vbscript");
        text(src);
        return this;
    }

}
