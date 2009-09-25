package net.bodz.xml.extb;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;

/**
 * @see ContentHandler
 */
@Deprecated
public interface SimpleHandler {

    /**
     * The namespace-prefixes or whatever features must be enabled in SAX.
     * 
     * For namespace prefixes which isn't defined, the <code>uri</code> is equal
     * to the prefix name.
     * 
     * @param uri
     *            empty string for default namespace
     * @param name
     *            never empty
     * @return another handler instance, or <code>null</code> to continue using
     *         this handler. The handler instance will be restored to
     *         <code>this</code> after the corresponding element is
     *         <code>leave</code>d.
     */
    SimpleHandler beginTag(String uri, String name, Attributes attributes);

    void endTag();

    void text(char[] buf, int off, int len);

    void ignorableWhitespace(char[] buf, int off, int len);

    void prepInfo(String target, String data);

    String resolve(String entity);

}
