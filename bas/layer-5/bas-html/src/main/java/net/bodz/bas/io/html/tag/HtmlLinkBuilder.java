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
        type("text/css");
        rel("stylesheet");
        href(href);
        return this;
    }

}
