package net.bodz.bas.potato.ref;

import net.bodz.bas.potato.element.IProperty;
import net.bodz.bas.repr.form.SortOrder;

public class PropertyRefMap
        extends AbstractPropertyRefMap<PropertyRefEntry<?>> {

    public PropertyRefMap(IRefEntry<?> objRef, SortOrder order) {
        super(objRef, order);
    }

    @Override
    protected PropertyRefEntry<?> createPropertyEntry(IRefEntry<?> instanceRef, IProperty property) {
        return new PropertyRefEntry<Object>(instanceRef, property);
    }

}
