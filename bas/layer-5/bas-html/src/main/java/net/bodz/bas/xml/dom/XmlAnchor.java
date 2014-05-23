package net.bodz.bas.xml.dom;

public class XmlAnchor
        extends AbstractXmlTag<XmlAnchor> {

    public XmlAnchor(IXmlTag parent) {
        super(parent);
    }

    @Override
    public XmlNodeType getType() {
        return XmlNodeType.ANCHOR;
    }

}
