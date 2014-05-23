package net.bodz.bas.xml.dom;

public class XmlComment
        extends AbstractXmlNode<XmlComment> {

    private String content;

    public XmlComment(IXmlTag parent, String content) {
        super(parent);
        this.content = content;
    }

    @Override
    public XmlNodeType getType() {
        return XmlNodeType.COMMENT;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
