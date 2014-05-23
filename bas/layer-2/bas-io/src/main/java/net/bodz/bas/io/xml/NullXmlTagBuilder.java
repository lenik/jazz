package net.bodz.bas.io.xml;

public class NullXmlTagBuilder
        implements IXmlTagBuilder {

    @Override
    public IXmlTagBuilder start() {
        return this;
    }

    @Override
    public IXmlTagBuilder end() {
        return this;
    }

    @Override
    public int getExit() {
        return 0;
    }

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

    @Override
    public IXmlTagBuilder text(String format, Object... args) {
        return this;
    }

}
