package net.bodz.bas.potato.ref;

import java.util.LinkedHashSet;
import java.util.Set;

import net.bodz.bas.potato.PotatoLoader;
import net.bodz.bas.potato.element.IProperty;
import net.bodz.bas.potato.element.IType;

public class InstanceProperties
        implements IRefEntries {

    private final IType type;
    private final Object instance;

    public InstanceProperties(Object instance) {
        if (instance == null)
            throw new NullPointerException("instance");
        Class<? extends Object> clazz = instance.getClass();
        IType type = PotatoLoader.getType(clazz);
        this.type = type;
        this.instance = instance;
    }

    public InstanceProperties(IType type, Object instance) {
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

    @Override
    public <T> IRefEntry<T> get(String propertyName) {
        IProperty property = type.getProperty(propertyName);
        PropertyRefEntry<T> entry = new PropertyRefEntry<T>(instance, property);
        return entry;
    }

}
