package net.bodz.bas.html.dom.tag;

import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.dom.tag.gen._MutableLink;

public class MutableLink
        extends _MutableLink<MutableLink> {

    public MutableLink(IHtmlTag parent, String tagName) {
        super(parent, tagName);
    }

    public MutableLink css(String href) {
        if (href == null)
            throw new NullPointerException("href");
        rel("stylesheet");
        type("text/css");
        href(href);
        return this;
    }

}
