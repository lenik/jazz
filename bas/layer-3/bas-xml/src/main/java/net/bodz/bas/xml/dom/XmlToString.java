package net.bodz.bas.xml.dom;

public class XmlToString
        extends AbstractXmlNode<XmlToString> {

    private Object content;

    public XmlToString(IXmlTag parent, Object content) {
        super(parent);
        this.content = content;
    }

    @Override
    public XmlNodeType getType() {
        return XmlNodeType.TEXT;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

}
