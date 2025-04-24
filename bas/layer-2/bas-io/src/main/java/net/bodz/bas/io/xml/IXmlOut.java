package net.bodz.bas.io.xml;

import java.io.Closeable;
import java.io.Flushable;
import java.util.Map;

public interface IXmlOut
        extends Flushable,
                Closeable {

    XmlDoc getDoc();

    String getTagName();

    IXmlOut getParent();

    IXmlOut dtd(String tag, String data);

    IXmlOut pi(String target, String data);

    /**
     * Write the begin part of the tag pair, for example, <code>&lt;tag attr='val' ...&gt;</code>.
     * <p>
     * A tag name stack is used to remember names from previous calls.
     *
     * @param name The tag name, non-<code>null</code>.
     * @return Child out node.
     */
    IXmlOut begin(String name);

    /**
     * @return parent out node.
     */
    IXmlOut end();

    IXmlOut attrs(Map<String, ?> attributes);

    IXmlOut attr(String name, String value);

    IXmlOut attr(String name, Object value);

    IXmlOut id(String id);

// IXmlOut text();

    IXmlOut text(String str);

    IXmlOut text(Object str);

    IXmlOut textf(String fmt, Object... args);

    IXmlOut textln(String str);

    IXmlOut textln(Object str);

    IXmlOut cdata(String cdata);

    IXmlOut comment(String str);

    void verbatim(String str);

    void indent(int level);

    IXmlOut NULL = new NullXmlOut();

}
