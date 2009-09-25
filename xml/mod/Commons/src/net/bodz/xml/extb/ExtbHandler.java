package net.bodz.xml.extb;

import static net.bodz.xml.extb.ExceptionType.ERROR;
import static net.bodz.xml.extb.ExceptionType.FATAL;
import static net.bodz.xml.extb.ExceptionType.WARNING;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.ext.DefaultHandler2;

public class ExtbHandler extends DefaultHandler2 {

    ExtbParser parser;

    public ExtbHandler(ExtbParser parser) {
        this.parser = parser;
    }

    @Override
    public final void startElement(String uri, String localName, String qName,
            Attributes attributes) throws SAXException {
        ExtbHandler h = startTag(uri, localName, attributes);
        // h/stack
    }

    @Override
    public final void endElement(String uri, String localName, String qName)
            throws SAXException {
        endTag(uri, localName);
    }

    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        String text = new String(ch, start, length);
        text(text);
    }

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
    protected ExtbHandler startTag(String uri, String name,
            Attributes attributes) {
        return this;
    }

    protected void endTag(String uri, String name) {
    }

    protected void text(String s) {
    }

    @Override
    public void warning(SAXParseException e) throws SAXException {
//        parser.reportError(WARNING, e);
    }

    @Override
    public void error(SAXParseException e) throws SAXException {
//        parser.reportError(ERROR, e);
    }

    @Override
    public void fatalError(SAXParseException e) throws SAXException {
//        parser.reportError(FATAL, e);
    }

}
