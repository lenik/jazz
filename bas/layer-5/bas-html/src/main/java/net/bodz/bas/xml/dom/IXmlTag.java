package net.bodz.bas.xml.dom;

import java.util.Map;

public interface IXmlTag
        extends IXmlNode {

    String getTagName();

    Map<String, String> getAttributeMap();

    IXmlTag _name(String tagName);

    IXmlTag attr(String name, String value);

    IXmlTag attr(String name, Object value);

    void at(int position);

    boolean remove(IXmlNode child);

    IXmlTag insert(IXmlNode child);

    IXmlTag insert(String tagName);

    XmlPI pi(String target);

    IXmlTag text(String content);

    IXmlTag text(Object content);

    IXmlTag text(String format, Object... args);

    void print(String s);

    void print(Object s);

    void println(String s);

    void println(Object s);

    IXmlTag comment(String content);

    IXmlTag cdata(String content);

    IXmlTag verbatim(String content);

}
