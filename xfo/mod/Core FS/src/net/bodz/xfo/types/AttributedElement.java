package net.bodz.xfo.types;

import java.util.Set;
import java.util.Map.Entry;

public interface AttributedElement {

    boolean hasAttribute(String id);

    String getAttributeText(String id);

    Set<String> getAttributeIds();

    Set<Entry<String, String>> getAttributeEntries();

}
