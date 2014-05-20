package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.xml.IXmlTagBuilder;

public class HtmlABuilder
        extends _HtmlABuilder<HtmlABuilder> {

    public HtmlABuilder(IXmlTagBuilder o) {
        super(o);
    }

    public HtmlABuilder nofollow() {
        rel("nofollow");
        return this;
    }

}
