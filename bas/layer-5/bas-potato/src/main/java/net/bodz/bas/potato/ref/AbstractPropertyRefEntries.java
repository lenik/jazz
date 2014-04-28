package net.bodz.bas.potato.ref;

import java.util.LinkedHashSet;
import java.util.Set;

import net.bodz.bas.potato.PotatoTypes;
import net.bodz.bas.potato.element.IProperty;
import net.bodz.bas.potato.element.IType;

public abstract class AbstractPropertyRefEntries<entry_t extends IRefEntry<?>>
        implements IRefEntries {

    private final IType type;
    private final Object instance;

    public AbstractPropertyRefEntries(Object instance) {
        if (instance == null)
            throw new NullPointerException("instance");
        Class<? extends Object> clazz = instance.getClass();
        IType type = PotatoTypes.getInstance().forClass(clazz);
        this.type = type;
        this.instance = instance;
    }

    public AbstractPropertyRefEntries(IType type, Object instance) {
        if (type == null)
            throw new NullPointerException("type");
        if (instance == null)
            throw new NullPointerException("instance");
        this.type = type;
        this.instance = instance;
    }

    @Override
    public Set<String> keySet() {
        Set<String> names = new LinkedHashSet<String>();
        for (IProperty property : type.getProperties())
            names.add(property.getName());
        return names;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> IRefEntry<T> get(String propertyName) {
        IProperty property = type.getProperty(propertyName);
        PropertyRefEntry entry = new PropertyRefEntry(instance, property);
        return (IRefEntry<T>) entry;
    }

}
