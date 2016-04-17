package net.bodz.bas.html.dom.tag;

import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.dom.tag.gen._MutableScript;

public class MutableScript
        extends _MutableScript<MutableScript> {

    public MutableScript(IHtmlTag parent, String tagName) {
        super(parent, tagName);
        text("");
    }

    public MutableScript javascript(String src) {
        type("text/javascript");
        text(src);
        return this;
    }

    public MutableScript javascriptSrc(String href) {
        if (href == null)
            throw new NullPointerException("href");
        type("text/javascript"); // This is also the default.
        src(href);
        return this;
    }

    public MutableScript vbscript(String src) {
        type("text/vbscript");
        text(src);
        return this;
    }

}
