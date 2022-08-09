package net.bodz.bas.fmt.xml.xq;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import net.bodz.bas.repr.form.SortOrder;
import net.bodz.bas.t.variant.AbstractVariantMap;

public class QElementMap
        extends AbstractVariantMap<String>
        implements
            IElementMap {

    Map<String, IElement> map;

    public QElementMap() {
        this(SortOrder.NONE);
    }

    public QElementMap(SortOrder order) {
        map = order.newMap();
    }

    public QElementMap(Map<String, IElement> map) {
        this.map = map;
    }

    public Map<String, IElement> getMap() {
        return map;
    }

    @Override
    public IElement getElement(String key) {
        IElement element = map.get(key);
        if (element == null)
            return NullElement.INSTANCE;
        else
            return element;
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public String get(Object key) {
        IElement element = map.get(key);
        if (element == null)
            return null;
        return element.getTextContent();
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public IElement put(String key, IElement value) {
        return map.put(key, value);
    }

    @Override
    public IElement remove(String key) {
        return map.remove(key);
    }

    @Override
    public void putAll(Map<String, ? extends IElement> m) {
        map.putAll(m);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Set<String> keySet() {
        return map.keySet();
    }

    @Override
    public Collection<IElement> values() {
        return map.values();
    }

    @Override
    public Set<Entry<String, IElement>> entrySet() {
        return map.entrySet();
    }

}
