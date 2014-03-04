package net.bodz.bas.potato.ref;

import net.bodz.bas.gui.dom1.IGUIRefEntry;
import net.bodz.bas.potato.element.IProperty;

public class PropertyGUIRefMap
        extends AbstractPropertyRefMap<PropertyGUIRefEntry>
        implements IGUIRefEntries {

    private static final long serialVersionUID = 1L;

    public PropertyGUIRefMap(Class<?> clazz, Object instance, Boolean order) {
        super(clazz, instance, order);
    }

    @Override
    public <T> IGUIRefEntry<T> get(String name) {
        return (IGUIRefEntry<T>) super.get(name);
    }

    @Override
    protected PropertyGUIRefEntry createPropertyEntry(Object instance, IProperty property) {
        PropertyGUIRefEntry entry = new PropertyGUIRefEntry(instance, property);
        return entry;
    }

}
