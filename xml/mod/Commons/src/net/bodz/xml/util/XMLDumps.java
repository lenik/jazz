package net.bodz.xml.util;

import java.util.Iterator;

import net.bodz.xml.a.XMLDump;

import org.dom4j.Attribute;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.tree.BaseElement;
import org.dom4j.tree.DefaultAttribute;
import org.dom4j.tree.DefaultElement;

public class XMLDumps {

    static Element toElement(Object o) {
        if (o == null)
            throw new NullPointerException("o");
        Class<?> clazz = o.getClass();
        XMLDump d = clazz.getAnnotation(XMLDump.class);
        String rootPath = d.value();
        if (rootPath == null)
            rootPath = clazz.getSimpleName();
        Node _root = expand(null, rootPath);
        if (!(_root instanceof Element))
            throw new IllegalArgumentException("Root path for class must be element: " + rootPath);
        Element root = (Element) _root;
        root.addAttribute("type", clazz.getName());

        // scan children...
        return root;
    }

    @SuppressWarnings("unchecked")
    static Node expand(Element start, String path) {
        Node node = start;
        String[] segments = path.split("/");
        for (String s : segments) {
            boolean attribute = false;
            boolean multiple = false;
            if (s.startsWith("@")) {
                attribute = true;
                s = s.substring(1);
            }
            if (s.endsWith("*")) {
                multiple = true;
                s = s.substring(0, s.length() - 1);
            }
            if (s.isEmpty())
                throw new IllegalArgumentException("bad segment in path: " + path);
            if (node == null)
                if (attribute)
                    node = new DefaultAttribute(s, null);
                else
                    node = new BaseElement(s);
            else if (node instanceof Attribute)
                throw new IllegalArgumentException("can't go into attribute node: " + path);
            else {
                Element elm = (Element) node;
                Iterator<Element> iter = elm.elementIterator(s);
                Element child = null;
                if (multiple) // Reusing existed child.
                    while (iter.hasNext())
                        child = iter.next();
                if (child == null) {
                    child = new DefaultElement(s);
                    elm.add(child);
                }
                node = child;
            }
        }
        return node;
    }

}
