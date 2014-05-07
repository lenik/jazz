package net.bodz.bas.potato.ref;

import net.bodz.bas.potato.element.IProperty;

public class UiPropertyRefMap
        extends AbstractPropertyRefMap<UiPropertyRef<Object>>
        implements IUiRefEntries {

    private static final long serialVersionUID = 1L;

    public UiPropertyRefMap(IRefEntry<?> objRef, Boolean order) {
        super(objRef, order);
    }

    @Override
    public <T> UiPropertyRef<T> get(String name) {
        return (UiPropertyRef<T>) super.get(name);
    }

    @Override
    protected UiPropertyRef<Object> createPropertyEntry(IRefEntry<?> instanceRef, IProperty property) {
        UiPropertyRef<Object> entry = new UiPropertyRef<Object>(instanceRef, property);
        return entry;
    }

}
