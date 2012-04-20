package net.bodz.bas.potato.spi.reflect;

import java.lang.reflect.Field;

import net.bodz.bas.potato.traits.AbstractPropertyMap;

public class ReflectPropertyMap
        extends AbstractPropertyMap {

    private static final long serialVersionUID = 1L;

    public ReflectPropertyMap(Class<?> clazz) {
        this(clazz.getFields());
    }

    public ReflectPropertyMap(Field... fields) {
        for (Field field : fields) {
            String name = field.getName();
            ReflectProperty reflectProperty = new ReflectProperty(field);
            put(name, reflectProperty);
        }
    }

}
