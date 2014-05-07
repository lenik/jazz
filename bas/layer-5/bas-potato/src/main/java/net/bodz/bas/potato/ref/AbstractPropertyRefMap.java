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

    private IRefEntry<?> objRef;

    private boolean autoImport = true;
    private boolean autoImported;
    private int newImportedCount;

    public AbstractPropertyRefMap(IRefEntry<?> objRef, Boolean order) {
        super(Collections.<String, entry_t> createMap(order));
        if (objRef == null)
            throw new NullPointerException("objRef");
        this.objRef = objRef;
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
        Class<?> objType = objRef.getValueType();
        IType type = PotatoTypes.getInstance().forClass(objType);
        for (IProperty property : type.getProperties()) {
            int modifiers = property.getModifiers();
            if ((modifiers & mask) == selection) {
                String name = property.getName();
                entry_t entry = createPropertyEntry(objRef, property);

                entry_t old = super.put(name, entry);
                if (old == null)
                    newImportedCount++;
            }
        }
        autoImported = true;
    }

    protected abstract entry_t createPropertyEntry(IRefEntry<?> instanceRef, IProperty property);

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(size() + " entries in the map (" + newImportedCount + " new imported):\n");
        for (Entry<?, ?> entry : entrySet())
            sb.append("  " + entry.getKey() + ": " + entry.getValue() + "\n");
        return sb.toString();
    }

}
