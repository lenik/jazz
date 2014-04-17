package net.bodz.bas.io.xml;

public class XmlOutTagBuffer
        implements IXmlTagBuilder {

    IXmlOut out;
    int exit = 1;
    String text;

    public XmlOutTagBuffer(IXmlOut out) {
        this.out = out;
    }

    @Override
    public IXmlTagBuilder start() {
        exit = 0;
        return this;
    }

    @Override
    public IXmlTagBuilder end() {
        exit++;
        return this;
    }

    @Override
    public int getExit() {
        return exit;
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

    @Override
    public IXmlTagBuilder text(Object content) {
        this.text = content == null ? null : content.toString();
        return this;
    }

}
