package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.xml.IXmlTagBuilder;

public class HtmlScriptBuilder
        extends _HtmlScriptBuilder<HtmlScriptBuilder> {

    public HtmlScriptBuilder(IXmlTagBuilder o) {
        super(o);
    }

    public HtmlScriptBuilder javascriptSrc(String href) {
        if (href == null)
            throw new NullPointerException("href");
        type("text/javascript"); // This is also the default.
        src(href);
        return this;
    }

}
