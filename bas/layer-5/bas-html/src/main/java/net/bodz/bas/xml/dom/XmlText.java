package net.bodz.bas.xml.dom;

public class XmlText
        extends AbstractXmlNode<XmlText> {

    private String content;

    public XmlText(IXmlTag parent, String content) {
        super(parent);
        this.content = content;
    }

    @Override
    public XmlNodeType getType() {
        return XmlNodeType.TEXT;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
