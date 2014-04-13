package net.bodz.bas.io.xml;

public interface IXmlTagBuilder {

    IXmlTagBuilder attr(String name, Object value);

    IXmlTagBuilder id(String id);

    IXmlTagBuilder tagText(String text);

    IXmlTagBuilder NULL = new NullXmlTagBuilder();

}
