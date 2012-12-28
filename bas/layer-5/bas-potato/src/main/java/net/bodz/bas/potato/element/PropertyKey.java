package net.bodz.bas.potato.element;

import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Map;

import net.bodz.bas.c.object.Nullables;

public final class PropertyKey
        implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String name;

    public PropertyKey(String name) {
        this.name = name;
    }

    public boolean isDefaultProperty() {
        return name == null;
    }

    public String getName() {
        return name;
    }

    public boolean isMatched(Field field) {
        return field.getName().equals(name);
    }

    public boolean isMatched(PropertyDescriptor propertyDescriptor) {
        return propertyDescriptor.getName().equals(name);
    }

    public boolean isMatched(Map.Entry<String, ?> mapEntry) {
        String key = mapEntry.getKey();
        return Nullables.equals(name, key);
    }

    @Override
    public int hashCode() {
        int result = 31;
        if (name != null)
            result += name.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof PropertyKey) {
            PropertyKey o = (PropertyKey) obj;
            return Nullables.equals(name, o.name);
        }
        return false;
    }

    @Override
    public String toString() {
        return "property=" + name;
    }

}
