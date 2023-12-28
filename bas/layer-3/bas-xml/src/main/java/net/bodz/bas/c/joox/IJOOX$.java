package net.bodz.bas.c.joox;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URI;
import java.net.URL;
import java.nio.file.Path;

import org.joox.Context;
import org.joox.JOOX;
import org.joox.Match;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public interface IJOOX$
        extends
            IJOOX_static1 {

    /**
     * Wrap a new empty document
     */
    @Override
    default Match $() {
        return JOOX.$();
    }

    /**
     * Wrap a JAXB-marshallable element in a jOOX {@link Match} element set
     *
     * @see #content(Object)
     * @see Match#content(Object)
     */
    @Override
    default Match $(Object object) {
        return JOOX.$(object);
    }

    /**
     * Create a new DOM element in an independent document
     */
    @Override
    default Match $(String name) {
        return JOOX.$(name);
    }

    /**
     * Create a new DOM element in an independent document
     */
    @Override
    default Match $(String name, String content) {
        return JOOX.$(name, content);
    }

    /**
     * Create a new DOM element in an independent document
     * <p>
     * The added content is cloned into the new document
     */
    @Override
    default Match $(String name, Element... content) {
        return JOOX.$(name, content);
    }

    /**
     * Create a new DOM element in an independent document
     * <p>
     * The added content is cloned into the new document
     */
    @Override
    default Match $(String name, Match... content) {
        return JOOX.$(name, content);
    }

    /**
     * Wrap a DOM document in a jOOX {@link Match} element set
     */
    @Override
    default Match $(Document document) {
        return JOOX.$(document);
    }

    /**
     * Wrap a DOM element in a jOOX {@link Match} element set
     */
    @Override
    default Match $(Element element) {
        return JOOX.$(element);
    }

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
    @Override
    default Match $(Node node) {
        return JOOX.$(node);
    }

    /**
     * Wrap a DOM {@link NodeList} in a jOOX {@link Match} element set
     * <p>
     * If the supplied NodeList is empty or null, then an empty Match is created
     */
    @Override
    default Match $(NodeList list) {
        return JOOX.$(list);
    }

    /**
     * Convenience method for calling <code>$(context.element())</code>
     */
    @Override
    default Match $(Context context) {
        return JOOX.$(context);
    }

    /**
     * Convenience method for calling <code>$(match)</code>
     */
    @Override
    default Match $(Match match) {
        return JOOX.$(match);
    }

    /**
     * Convenience method for calling <code>$(url.openStream())</code>
     */
    @Override
    default Match $(URL url)
            throws SAXException, IOException {
        return JOOX.$(url);
    }

    /**
     * Convenience method for calling <code>$(new File(uri))</code>
     */
    @Override
    default Match $(URI uri)
            throws SAXException, IOException {
        return JOOX.$(uri);
    }

    /**
     * Read a DOM document from a file into a {@link Match} element set
     *
     * @throws IOException
     * @throws SAXException
     */
    @Override
    default Match $(File file)
            throws SAXException, IOException {
        return JOOX.$(file);
    }

    /**
     * Read a DOM document from a file into a {@link Match} element set
     *
     * @throws IOException
     * @throws SAXException
     */
    @Override
    default Match $(Path path)
            throws SAXException, IOException {
        return JOOX.$(path);
    }

    /**
     * Read a DOM document from a stream into a {@link Match} element set
     *
     * @throws IOException
     * @throws SAXException
     */
    @Override
    default Match $(InputStream stream)
            throws SAXException, IOException {
        return JOOX.$(stream);
    }

    /**
     * Read a DOM document from a reader into a {@link Match} element set
     *
     * @throws IOException
     * @throws SAXException
     */
    @Override
    default Match $(Reader reader)
            throws SAXException, IOException {
        return JOOX.$(reader);
    }

    /**
     * Read a DOM document from a file into a {@link Match} element set
     *
     * @throws IOException
     * @throws SAXException
     */
    @Override
    default Match $(InputSource source)
            throws SAXException, IOException {
        return JOOX.$(source);
    }

}