package net.bodz.bas.fmt.xml.xq;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import net.bodz.bas.t.variant.ILookupMap;

public interface IElementMap
        extends
            ILookupMap<String, Object> {

    IElement getElement(String key);

    int size();

    boolean isEmpty();

    IElement put(String key, IElement value);

    IElement remove(String key);

    void putAll(Map<String, ? extends IElement> m);

    void clear();

    Collection<IElement> values();

    Set<Entry<String, IElement>> entrySet();

}
