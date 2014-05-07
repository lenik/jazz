package net.bodz.bas.potato.ref;

import java.util.LinkedHashSet;
import java.util.Set;

import net.bodz.bas.potato.PotatoTypes;
import net.bodz.bas.potato.element.IProperty;
import net.bodz.bas.potato.element.IType;

public abstract class AbstractPropertyRefEntries<entry_t extends IRefEntry<?>>
        implements IRefEntries {

    private final IType type;
    private final IRefEntry<?> instanceRef;

    public AbstractPropertyRefEntries(IRefEntry<?> instanceRef) {
        if (instanceRef == null)
            throw new NullPointerException("instanceRef");
        Class<?> instanceClass = instanceRef.getValueType();
        IType type = PotatoTypes.getInstance().forClass(instanceClass);
        this.type = type;
        this.instanceRef = instanceRef;
    }

    public AbstractPropertyRefEntries(IType type, IRefEntry<?> instanceRef) {
        if (type == null)
            throw new NullPointerException("type");
        if (instanceRef == null)
            throw new NullPointerException("instanceRef");
        this.type = type;
        this.instanceRef = instanceRef;
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
        PropertyRefEntry<T> entry = new PropertyRefEntry<T>(instanceRef, property);
        return entry;
    }

}
