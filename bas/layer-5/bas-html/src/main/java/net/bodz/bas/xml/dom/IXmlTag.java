package net.bodz.bas.xml.dom;

import java.util.Map;

public interface IXmlTag
        extends IXmlNode {

    IXmlTag getRoot();

    /**
     * @return <code>null</code> for pseudo-tag.
     */
    String getTagName();

    Map<String, String> getAttributeMap();

    /**
     * A term tag like <code>&lt;br&gt;</code> and <code>&lt;hr&gt;</code> have no child node.
     * 
     * A non-term tag have child nodes.
     */
    boolean isTerm();

    IXmlTag _name(String tagName);

    IXmlTag attr(String name, String value);

    IXmlTag attr(String name, Object value);

    /**
     * Move the cursor to the specified position.
     */
    void at(int position);

    boolean remove(IXmlNode child);

    IXmlTag insert(IXmlNode child);

    IXmlTag insert(String tagName);

    XmlPI pi(String target);

    IXmlTag text(String content, String nullVerbatim);

    IXmlTag text(String content);

    IXmlTag text(Object content);

    IXmlTag textf(String format, Object... args);

    void print(String s);

    void print(Object s);

    void println(String s);

    void println(Object s);

    IXmlTag comment(String content);

    IXmlTag cdata(String content);

    IXmlTag verbatim(String content);

}
