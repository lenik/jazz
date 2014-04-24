package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.xml.IXmlTagBuilder;

public class HtmlScriptBuilder
        extends _HtmlScriptBuilder<HtmlScriptBuilder> {

    public HtmlScriptBuilder(IXmlTagBuilder o) {
        super(o);
    }

    public HtmlScriptBuilder javascript(String src) {
        type("text/javascript");
        text(src);
        return this;
    }

    public HtmlScriptBuilder javascriptSrc(String href) {
        if (href == null)
            throw new NullPointerException("href");
        type("text/javascript"); // This is also the default.
        src(href);
        return this;
    }

    public HtmlScriptBuilder vbscript(String src) {
        type("text/vbscript");
        text(src);
        return this;
    }

}
