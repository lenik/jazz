package net.bodz.bas.c.joox;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URI;
import java.net.URL;
import java.nio.file.Path;

import org.joox.Context;
import org.joox.Match;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public interface IJOOX_static1 {

    /**
     * Wrap a new empty document
     */
    Match $();

    /**
     * Wrap a JAXB-marshallable element in a jOOX {@link Match} element set
     *
     * @see #content(Object)
     * @see Match#content(Object)
     */
    Match $(Object object);

    /**
     * Create a new DOM element in an independent document
     */
    Match $(String name);

    /**
     * Create a new DOM element in an independent document
     */
    Match $(String name, String content);

    /**
     * Create a new DOM element in an independent document
     * <p>
     * The added content is cloned into the new document
     */
    Match $(String name, Element... content);

    /**
     * Create a new DOM element in an independent document
     * <p>
     * The added content is cloned into the new document
     */
    Match $(String name, Match... content);

    /**
     * Wrap a DOM document in a jOOX {@link Match} element set
     */
    Match $(Document document);

    /**
     * Wrap a DOM element in a jOOX {@link Match} element set
     */
    Match $(Element element);

    /**
     * Wrap a DOM {@link Node} in a jOOX {@link Match} element set
     * <p>
     * Supported node types are
     * <ul>
     * <li>{@link Document} : see {@link #$(Document)}</li>
     * <li>{@link Element} : see {@link #$(Element)}</li>
     * </ul>
     * If the supplied Node is of any other type, then an empty Match is created
     */
    Match $(Node node);

    /**
     * Wrap a DOM {@link NodeList} in a jOOX {@link Match} element set
     * <p>
     * If the supplied NodeList is empty or null, then an empty Match is created
     */
    Match $(NodeList list);

    /**
     * Convenience method for calling <code>$(context.element())</code>
     */
    Match $(Context context);

    /**
     * Convenience method for calling <code>$(match)</code>
     */
    Match $(Match match);

    /**
     * Convenience method for calling <code>$(url.openStream())</code>
     */
    Match $(URL url)
            throws SAXException, IOException;

    /**
     * Convenience method for calling <code>$(new File(uri))</code>
     */
    Match $(URI uri)
            throws SAXException, IOException;

    /**
     * Read a DOM document from a file into a {@link Match} element set
     *
     * @throws IOException
     * @throws SAXException
     */
    Match $(File file)
            throws SAXException, IOException;

    /**
     * Read a DOM document from a file into a {@link Match} element set
     *
     * @throws IOException
     * @throws SAXException
     */
    Match $(Path path)
            throws SAXException, IOException;

    /**
     * Read a DOM document from a stream into a {@link Match} element set
     *
     * @throws IOException
     * @throws SAXException
     */
    Match $(InputStream stream)
            throws SAXException, IOException;

    /**
     * Read a DOM document from a reader into a {@link Match} element set
     *
     * @throws IOException
     * @throws SAXException
     */
    Match $(Reader reader)
            throws SAXException, IOException;

    /**
     * Read a DOM document from a file into a {@link Match} element set
     *
     * @throws IOException
     * @throws SAXException
     */
    Match $(InputSource source)
            throws SAXException, IOException;

}