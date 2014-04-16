package net.bodz.bas.io.xml;

public class NullXmlTagBuilder
        implements IXmlTagBuilder {

    @Override
    public IXmlTagBuilder attr(String name, Object value) {
        return this;
    }

    @Override
    public IXmlTagBuilder id(String id) {
        return this;
    }

    @Override
    public IXmlTagBuilder text(String text) {
        return this;
    }

    @Override
    public IXmlTagBuilder text(Object content) {
        return this;
    }

}
