package net.bodz.bas.fmt.xml.xq;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import net.bodz.bas.t.iterator.PrefetchedIterator;

public class ChildrenIterator
        extends PrefetchedIterator<Element> {

    Element parent;
    Element next;

    public ChildrenIterator(Element parent) {
        if (parent == null)
            throw new NullPointerException("parent");
        this.parent = parent;
    }

    @Override
    protected Element fetch() {
        if (next == null)
            next = getFirstChildElement(parent);
        else
            next = getNextSiblingElement(next);
        if (next != null)
            return next;
        else
            return end();
    }

    public static Element getFirstChildElement(Element parent) {
        Node head = parent.getFirstChild();
        while (head != null) {
            if (head.getNodeType() == Node.ELEMENT_NODE)
                return (Element) head;
            else
                head = head.getNextSibling(); // ... the first element.
        }
        return null;
    }

    public static Element getNextSiblingElement(Element head) {
        Node next = head.getNextSibling();
        while (next != null) {
            if (next.getNodeType() == Node.ELEMENT_NODE)
                return (Element) next;
            else
                next = next.getNextSibling(); // ... the first element.
        }
        return null;
    }

}
