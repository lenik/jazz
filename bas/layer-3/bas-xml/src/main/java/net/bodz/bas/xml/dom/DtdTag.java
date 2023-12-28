package net.bodz.bas.xml.dom;

public class DtdTag
        extends AbstractXmlNode<DtdTag> {

    private String tagName;
    private String content;

    public DtdTag(IXmlTag parent, String tagName) {
        super(parent);
        this.tagName = tagName;
    }

    @Override
    public XmlNodeType getType() {
        return XmlNodeType.DTD_TAG;
    }

    public String getTagName() {
        return tagName;
    }

    public void _name(String tagName) {
        if (tagName == null)
            throw new NullPointerException("tagName");
        this.tagName = tagName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
