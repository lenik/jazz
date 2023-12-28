package net.bodz.bas.xml.dom;

public class XmlCData
        extends AbstractXmlNode<XmlCData> {

    private String content;

    public XmlCData(IXmlTag parent, String content) {
        super(parent);
        this.content = content;
    }

    @Override
    public XmlNodeType getType() {
        return XmlNodeType.CDATA;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
