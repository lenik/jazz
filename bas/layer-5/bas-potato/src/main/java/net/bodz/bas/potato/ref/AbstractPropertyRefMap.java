package net.bodz.bas.potato.ref;

import java.lang.reflect.Modifier;
import java.util.AbstractMap;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import net.bodz.bas.c.java.util.Collections;
import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.potato.PotatoTypes;
import net.bodz.bas.potato.element.IProperty;
import net.bodz.bas.potato.element.IType;

public abstract class AbstractPropertyRefMap<entry_t extends PropertyRefEntry<?>>
        extends AbstractMap<String, Object>
        implements IRefEntries {

    private IRefEntry<?> objRef;

    private Map<String, entry_t> cache;
    private boolean autoImport = true;
    private boolean autoImported;
    private int newImportedCount;

    public AbstractPropertyRefMap(IRefEntry<?> objRef, Boolean order) {
        cache = Collections.<String, entry_t> createMap(order);
        if (objRef == null)
            throw new NullPointerException("objRef");
        this.objRef = objRef;
    }

    @Override
    public Set<String> keySet() {
        load();
        return cache.keySet();
    }

    @Override
    public <T> PropertyRefEntry<T> getEntry(String name) {
        load();
        return (PropertyRefEntry<T>) cache.get(name);
    }

    public Collection<entry_t> getEntries() {
        load();
        return cache.values();
    }

    private synchronized void load() {
        if (autoImport && !autoImported) {
            importProperties();
            autoImported = true;
        }
    }

    public final void importProperties() {
        importProperties(//
                Modifier.PRIVATE | Modifier.STATIC, //
                0);
    }

    public synchronized void importProperties(int mask, int selection) {
        Class<?> objType = objRef.getValueType();
        IType type = PotatoTypes.getInstance().forClass(objType);
        for (IProperty property : type.getProperties()) {
            int modifiers = property.getModifiers();
            if ((modifiers & mask) == selection) {
                String name = property.getName();
                entry_t entry = createPropertyEntry(objRef, property);

                entry_t old = cache.put(name, entry);
                if (old == null)
                    newImportedCount++;
            }
        }
        autoImported = true;
    }

    protected abstract entry_t createPropertyEntry(IRefEntry<?> instanceRef, IProperty property);

    /** ⇱ Implementation Of {@link Map}. */
    /* _____________________________ */static section.iface __MAP__;

    @Override
    public int size() {
        load();
        return cache.size();
    }

    @Override
    public Object get(Object key) {
        if (!(key instanceof String))
            return null;
        String name = (String) key;
        IRefEntry<Object> entry = getEntry(name);
        if (entry == null)
            return null;
        return entry.get();
    }

    @Override
    public Object put(String key, Object value) {
        if (!(key instanceof String))
            return null;
        String name = (String) key;
        IRefEntry<Object> entry = getEntry(name);
        if (entry == null)
            return null;
        Object old = entry.get();
        entry.set(value);
        return old;
    }

    @Override
    public Set<Entry<String, Object>> entrySet() {
        throw new NotImplementedException();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(cache.size() + " entries in the map (" + newImportedCount + " new imported):\n");
        for (Entry<?, ?> entry : cache.entrySet())
            sb.append("  " + entry.getKey() + ": " + entry.getValue() + "\n");
        return sb.toString();
    }

}
