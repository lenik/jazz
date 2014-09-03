package net.bodz.bas.potato.ref;

import net.bodz.bas.potato.element.IProperty;

public class PropertyRefMap
        extends AbstractPropertyRefMap<PropertyRefEntry<?>> {

    public PropertyRefMap(IRefEntry<?> objRef, Boolean order) {
        super(objRef, order);
    }

    @Override
    protected PropertyRefEntry<?> createPropertyEntry(IRefEntry<?> instanceRef, IProperty property) {
        return new PropertyRefEntry<Object>(instanceRef, property);
    }

}
