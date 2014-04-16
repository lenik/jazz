package net.bodz.bas.io.xml;

public class XmlOutTagBuffer
        implements IXmlTagBuilder {

    IXmlOut out;
    String text;

    public XmlOutTagBuffer(IXmlOut out) {
        this.out = out;
    }

    @Override
    public IXmlTagBuilder attr(String name, Object value) {
        out.attribute(name, value);
        return this;
    }

    @Override
    public IXmlTagBuilder id(String id) {
        return attr("id", id);
    }

    @Override
    public IXmlTagBuilder text(String text) {
        this.text = text;
        return this;
    }

}
