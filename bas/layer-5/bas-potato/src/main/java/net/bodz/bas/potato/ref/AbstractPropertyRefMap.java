package net.bodz.bas.potato.ref;

import java.lang.reflect.Modifier;

import net.bodz.bas.c.java.util.Collections;
import net.bodz.bas.potato.PotatoTypes;
import net.bodz.bas.potato.element.IProperty;
import net.bodz.bas.potato.element.IType;
import net.bodz.bas.proxy.java.util.DecoratedMap;

public abstract class AbstractPropertyRefMap<entry_t extends PropertyRefEntry<?>>
        extends DecoratedMap<String, entry_t>
        implements IRefEntries {

    private static final long serialVersionUID = 1L;

    private Class<?> clazz;
    private Object instance;

    private boolean autoImport = true;
    private boolean autoImported;
    private int newImportedCount;

    public AbstractPropertyRefMap(Class<?> clazz, Object instance, Boolean order) {
        super(Collections.<String, entry_t> createMap(order));
        if (clazz == null)
            throw new NullPointerException("clazz");
        if (instance == null)
            throw new NullPointerException("instance");
        this.clazz = clazz;
        this.instance = instance;
    }

    @Override
    public <T> IRefEntry<T> get(String name) {
        IRefEntry<T> entry = (IRefEntry<T>) super.get(name);
        if (entry != null)
            return entry;

        synchronized (this) {
            if (autoImport && !autoImported) {
                importProperties();
                autoImported = true;
                return get(name);
            }
        }
        return null;
    }

    public final void importProperties() {
        importProperties(//
                Modifier.PRIVATE | Modifier.STATIC, //
                0);
    }

    public synchronized void importProperties(int mask, int selection) {
        IType type = PotatoTypes.getInstance().forClass(clazz);
        for (IProperty property : type.getProperties()) {
            int modifiers = property.getModifiers();
            if ((modifiers & mask) == selection) {
                String name = property.getName();
                entry_t entry = createPropertyEntry(instance, property);

                entry_t old = super.put(name, entry);
                if (old == null)
                    newImportedCount++;
            }
        }
        autoImported = true;
    }

    protected abstract entry_t createPropertyEntry(Object instance, IProperty property);

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(size() + " entries in the map (" + newImportedCount + " new imported):\n");
        for (Entry<?, ?> entry : entrySet())
            sb.append("  " + entry.getKey() + ": " + entry.getValue() + "\n");
        return sb.toString();
    }

}
