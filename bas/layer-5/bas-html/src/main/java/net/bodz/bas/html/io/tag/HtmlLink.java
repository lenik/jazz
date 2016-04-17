package net.bodz.bas.html.io.tag;

import net.bodz.bas.html.io.HtmlDoc;
import net.bodz.bas.html.io.tag.gen._HtmlLink;

public class HtmlLink
        extends _HtmlLink<HtmlLink> {

    public HtmlLink(HtmlDoc doc) {
        super(doc);
    }

    public HtmlLink css(String href) {
        if (href == null)
            throw new NullPointerException("href");
        rel("stylesheet");
        type("text/css");
        href(href);
        return this;
    }

}
