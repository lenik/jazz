package net.bodz.bas.html.dom;

import net.bodz.bas.xml.dom.XmlNodeType;

public class HtmlDoc
        extends AbstractHtmlTag<HtmlDoc> {

    public HtmlDoc() {
        super(null);
    }

    @Override
    public XmlNodeType getType() {
        return XmlNodeType.ANCHOR;
    }

}
