package net.bodz.bas.potato.ref;

import java.lang.reflect.Modifier;

import net.bodz.bas.c.java.util.Collections;
import net.bodz.bas.potato.PotatoLoader;
import net.bodz.bas.potato.element.IProperty;
import net.bodz.bas.potato.element.IType;
import net.bodz.bas.proxy.java.util.DecoratedMap;

public abstract class AbstractPropertyRefMap<entry_t extends PropertyRefEntry>
        extends DecoratedMap<String, entry_t>
        implements IRefEntries {

    private static final long serialVersionUID = 1L;

    private Class<?> clazz;
    private Object instance;

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
        return (IRefEntry<T>) super.get(name);
    }

    public final void importProperties() {
        importProperties(//
                Modifier.PRIVATE | Modifier.STATIC, //
                0);
    }

    public void importProperties(int mask, int selection) {
        IType type = PotatoLoader.getType(clazz);
        for (IProperty property : type.getProperties()) {
            int modifiers = property.getModifiers();
            if ((modifiers & mask) == selection) {
                String name = property.getName();
                entry_t entry = createPropertyEntry(instance, property);
                super.put(name, entry);
            }
        }
    }

    protected abstract entry_t createPropertyEntry(Object instance, IProperty property);

}
