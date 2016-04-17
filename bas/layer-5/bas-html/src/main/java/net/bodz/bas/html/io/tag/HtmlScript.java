package net.bodz.bas.html.io.tag;

import net.bodz.bas.html.io.HtmlDoc;
import net.bodz.bas.html.io.tag.gen._HtmlScript;

public class HtmlScript
        extends _HtmlScript<HtmlScript> {

    public HtmlScript(HtmlDoc doc) {
        super(doc);
    }

    public HtmlScript javascript(String src) {
        type("text/javascript");
        text(src);
        return this;
    }

    public HtmlScript javascriptSrc(String href) {
        if (href == null)
            throw new NullPointerException("href");
        type("text/javascript"); // This is also the default.
        src(href);
        return this;
    }

    public HtmlScript vbscript(String src) {
        type("text/vbscript");
        text(src);
        return this;
    }

}
