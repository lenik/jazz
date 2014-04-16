package net.bodz.bas.io.xml;

public interface IXmlTagBuilder {

    IXmlTagBuilder attr(String name, Object value);

    IXmlTagBuilder id(String id);

    IXmlTagBuilder text(String text);

    IXmlTagBuilder NULL = new NullXmlTagBuilder();

}
