package net.bodz.bas.potato.element;

public abstract class AbstractPropertyMap
        implements IPropertyMap {

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder(getPropertyCount() * 100);
        for (IProperty property : getProperties()) {
            String name = property.getName();
            buf.append(name);
            buf.append("\n");
        }
        return buf.toString();
    }

}
