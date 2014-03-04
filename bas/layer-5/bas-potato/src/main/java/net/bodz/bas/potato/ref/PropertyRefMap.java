package net.bodz.bas.potato.ref;

import net.bodz.bas.potato.element.IProperty;

public class PropertyRefMap
        extends AbstractPropertyRefMap<PropertyRefEntry> {

    private static final long serialVersionUID = 1L;

    public PropertyRefMap(Class<?> clazz, Object instance, Boolean order) {
        super(clazz, instance, order);
    }

    @Override
    protected PropertyRefEntry createPropertyEntry(Object instance, IProperty property) {
        return new PropertyRefEntry(instance, property);
    }

}
