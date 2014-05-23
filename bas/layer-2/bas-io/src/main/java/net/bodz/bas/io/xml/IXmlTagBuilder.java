package net.bodz.bas.io.xml;

public interface IXmlTagBuilder {

    IXmlTagBuilder start();

    IXmlTagBuilder end();

    int getExit();

    IXmlTagBuilder attr(String name, Object value);

    IXmlTagBuilder id(String id);

    IXmlTagBuilder text(String text);

    IXmlTagBuilder text(Object content);

    IXmlTagBuilder text(String format, Object... args);

    IXmlTagBuilder NULL = new NullXmlTagBuilder();

}
