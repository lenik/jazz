package net.bodz.bas.c.javax.servlet;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import net.bodz.bas.t.pojo.Pair;

public class AttributesIterable
        implements Iterable<Map.Entry<String, Object>> {

    IAttributes attributes;

    public AttributesIterable(IAttributes attributes) {
        if (attributes == null)
            throw new NullPointerException("attributes");
        this.attributes = attributes;
    }

    @Override
    public Iterator<Entry<String, Object>> iterator() {
        return new AttributesIterator(attributes);
    }

}

class AttributesIterator
        implements Iterator<Map.Entry<String, Object>> {

    IAttributes attributes;
    Enumeration<String> names;
    String lastName;

    public AttributesIterator(IAttributes attributes) {
        this.attributes = attributes;
        this.names = attributes.getAttributeNames();
    }

    @Override
    public boolean hasNext() {
        return names.hasMoreElements();
    }

    @Override
    public Entry<String, Object> next() {
        String name = names.nextElement();
        Object value = attributes.getAttribute(name);
        lastName = name;
        return Pair.of(name, value);
    }

    @Override
    public void remove() {
        if (lastName == null)
            throw new IllegalStateException("Nothing to remove.");
        attributes.removeAttribute(lastName);
        lastName = null;
    }

}
