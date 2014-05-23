package net.bodz.bas.xml.dom;

public class XmlPI
        extends AbstractXmlNode<XmlPI> {

    private String target;
    private String content = "";

    public XmlPI(IXmlTag parent, String target) {
        super(parent);
        if (target == null)
            throw new NullPointerException("target");
        this.target = target;
    }

    @Override
    public XmlNodeType getType() {
        return XmlNodeType.PROCESS_INSTRUCTION;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        if (target == null)
            throw new NullPointerException("target");
        this.target = target;
    }

    public String getContent() {
        return content;
    }

    public XmlPI content(String content) {
        if (content == null)
            throw new NullPointerException("content");
        this.content = content;
        return this;
    }

    public void xml(String version, String encoding) {
        content("version=\"" + version + "\" encoding=\"" + encoding + "\"");
    }

}
