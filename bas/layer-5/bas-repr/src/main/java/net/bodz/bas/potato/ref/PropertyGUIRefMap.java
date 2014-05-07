package net.bodz.bas.potato.ref;

import net.bodz.bas.potato.element.IProperty;

public class PropertyGUIRefMap
        extends AbstractPropertyRefMap<PropertyGUIRefEntry<Object>>
        implements IGUIRefEntries {

    private static final long serialVersionUID = 1L;

    public PropertyGUIRefMap(IRefEntry<?> objRef, Boolean order) {
        super(objRef, order);
    }

    @Override
    public <T> PropertyGUIRefEntry<T> get(String name) {
        return (PropertyGUIRefEntry<T>) super.get(name);
    }

    @Override
    protected PropertyGUIRefEntry<Object> createPropertyEntry(IRefEntry<?> instanceRef, IProperty property) {
        PropertyGUIRefEntry<Object> entry = new PropertyGUIRefEntry<Object>(instanceRef, property);
        return entry;
    }

}
