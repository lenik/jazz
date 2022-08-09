package net.bodz.bas.fmt.xml.xq;

import org.w3c.dom.DOMException;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class NullNamedNodeMap
        implements
            NamedNodeMap {

    @Override
    public Node getNamedItem(String name) {
        return null;
    }

    @Override
    public Node setNamedItem(Node arg)
            throws DOMException {
        return null;
    }

    @Override
    public Node removeNamedItem(String name)
            throws DOMException {
        return null;
    }

    @Override
    public Node item(int index) {
        return null;
    }

    @Override
    public int getLength() {
        return 0;
    }

    @Override
    public Node getNamedItemNS(String namespaceURI, String localName)
            throws DOMException {
        return null;
    }

    @Override
    public Node setNamedItemNS(Node arg)
            throws DOMException {
        return null;
    }

    @Override
    public Node removeNamedItemNS(String namespaceURI, String localName)
            throws DOMException {
        return null;
    }

    public static final NullNamedNodeMap INSTANCE = new NullNamedNodeMap();

}
