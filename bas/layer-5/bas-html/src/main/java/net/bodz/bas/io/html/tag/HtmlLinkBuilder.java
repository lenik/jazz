package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.xml.IXmlTagBuilder;

public class HtmlLinkBuilder
        extends _HtmlLinkBuilder<HtmlLinkBuilder> {

    public HtmlLinkBuilder(IXmlTagBuilder o) {
        super(o);
    }

    public HtmlLinkBuilder css(String href) {
        if (href == null)
            throw new NullPointerException("href");
        rel("stylesheet");
        type("text/css");
        href(href);
        return this;
    }

}
