package net.bodz.bas.fmt.xml;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Iterator;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import net.bodz.bas.err.NoSuchKeyException;
import net.bodz.bas.meta.source.FnHelper;
import net.bodz.bas.t.iterator.PrefetchedIterator;

@FnHelper
public class DomFn {

    public static Iterable<Element> childElements(final Node node) {
        return new Iterable<Element>() {
            @Override
            public Iterator<Element> iterator() {
                return new ChildElementsIterator(node);
            }
        };
    }

    public static Element firstElement(Node node, String name) {
        for (Element child : childElements(node)) {
            String n = child.getLocalName();
            if (n.equals(name))
                return child;
        }
        return null;
    }

    public static Element firstElementIgnoreCase(Node node, String name) {
        for (Element child : childElements(node)) {
            String nodeName = child.getLocalName();
            if (nodeName.equalsIgnoreCase(name))
                return child;
        }
        return null;
    }

    public static String getString(Element element, String childName) {
        Element child = firstElement(element, childName);
        if (child == null)
            return null;
        return child.getTextContent();
    }

    static DecimalFormat decimalFormat;
    static {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator(',');
        symbols.setDecimalSeparator('.');
        String pattern = "#,##0.0#";
        decimalFormat = new DecimalFormat(pattern, symbols);
        decimalFormat.setParseBigDecimal(true);
    }

    public static BigDecimal getBigDecimal(Element element, String childName) {
        return getBigDecimal(element, childName, null);
    }

    public static BigDecimal getBigDecimal(Element element, String childName, BigDecimal defaultValue) {
        String str = getString(element, childName);
        if (str == null)
            return defaultValue;
        str = str.trim();
        if (str.isEmpty())
            return defaultValue;
        try {
            BigDecimal value = (BigDecimal) decimalFormat.parse(str);
            return value;
        } catch (ParseException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static class attr {

        public static float getFloat(Element element, String name) {
            return getFloat(element, name, null);
        }

        public static float getFloat(Element element, String name, Float fallback) {
            String s = element.getAttribute(name);
            if (s == null) {
                if (fallback == null)
                    throw new NoSuchKeyException(name);
                return fallback.floatValue();
            }
            return Float.parseFloat(s);
        }

    }

}

class ChildElementsIterator
        extends PrefetchedIterator<Element> {

    NodeList children;
    int length;
    int index = 0;

    public ChildElementsIterator(Node parent) {
        if (parent == null)
            throw new NullPointerException("parent");
        children = parent.getChildNodes();
        length = children.getLength();
    }

    @Override
    protected Element fetch() {
        for (;;) {
            if (index >= length)
                return end();
            Node childNode = children.item(index++);
            if (childNode.getNodeType() == Node.ELEMENT_NODE)
                return (Element) childNode;
        }
    }

}