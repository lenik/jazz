package net.bodz.bas.fmt.xml.xq;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import net.bodz.bas.err.ReadOnlyException;

public class NullElementMap
        implements
            IElementMap {

    @Override
    public Set<String> keySet() {
        return Collections.emptySet();
    }

    @Override
    public boolean containsKey(Object key) {
        return false;
    }

    @Override
    public Object get(Object key) {
        return null;
    }

    @Override
    public IElement getElement(String key) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public IElement put(String key, IElement value) {
        throw new ReadOnlyException();
    }

    @Override
    public IElement remove(String key) {
        return null;
    }

    @Override
    public void putAll(Map<String, ? extends IElement> m) {
        if (m.isEmpty())
            return;
        else
            throw new ReadOnlyException();
    }

    @Override
    public void clear() {
    }

    @Override
    public Collection<IElement> values() {
        return Collections.emptySet();
    }

    @Override
    public Set<Entry<String, IElement>> entrySet() {
        return Collections.emptySet();
    }

    public static final NullElementMap INSTANCE = new NullElementMap();

}
