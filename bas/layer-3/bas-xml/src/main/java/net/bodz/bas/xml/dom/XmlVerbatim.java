package net.bodz.bas.xml.dom;

public class XmlVerbatim
        extends AbstractXmlNode<XmlVerbatim> {

    private String content;

    public XmlVerbatim(IXmlTag parent, String content) {
        super(parent);
        this.content = content;
    }

    @Override
    public XmlNodeType getType() {
        return XmlNodeType.VERBATIM;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
