package net.bodz.bas.potato.ref;

import net.bodz.bas.potato.element.IProperty;
import net.bodz.bas.repr.form.SortOrder;

public class UiPropertyRefMap
        extends AbstractPropertyRefMap<UiPropertyRef<Object>>
        implements IUiRefEntries {

    public UiPropertyRefMap(IRefEntry<?> objRef, SortOrder order) {
        super(objRef, order);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> UiPropertyRef<T> getEntry(String name) {
        return (UiPropertyRef<T>) super.getEntry(name);
    }

    @Override
    protected UiPropertyRef<Object> createPropertyEntry(IRefEntry<?> instanceRef, IProperty property) {
        UiPropertyRef<Object> entry = new UiPropertyRef<Object>(instanceRef, property);
        return entry;
    }

}
