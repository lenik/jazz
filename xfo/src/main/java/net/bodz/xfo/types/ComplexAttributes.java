package net.bodz.xfo.types;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.Map.Entry;

public class ComplexAttributes implements AttributedElement {

    private AttributedElement[] elements;

    public ComplexAttributes(AttributedElement... elements) {
        if (elements == null)
            throw new NullPointerException("elements");
        this.elements = elements;
    }

    @Override
    public Set<String> getAttributeIds() {
        Set<String> merge = new HashSet<String>();
        for (AttributedElement e : elements)
            merge.addAll(e.getAttributeIds());
        return Collections.unmodifiableSet(merge);
    }

    @Override
    public Set<Entry<String, String>> getAttributeEntries() {
        HashSet<Entry<String, String>> merge = new HashSet<Entry<String, String>>();
        for (AttributedElement e : elements)
            merge.addAll(e.getAttributeEntries());
        return Collections.unmodifiableSet(merge);
    }

    @Override
    public boolean hasAttribute(String id) {
        for (AttributedElement e : elements)
            if (e.hasAttribute(id))
                return true;
        return false;
    }

    @Override
    public String getAttributeText(String id) {
        for (AttributedElement e : elements) {
            String text = e.getAttributeText(id);
            if (text != null)
                return text;
        }
        return null;
    }

}
