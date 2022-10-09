package net.bodz.lily.entity.type;

import java.lang.reflect.Field;

public class ReflectFieldOrder
        extends AbstractFieldOrder {

    public ReflectFieldOrder(Class<?> clazz) {
        super(clazz);
    }

    @Override
    protected void findFields(Class<?> clazz) {
        for (Field field : clazz.getDeclaredFields()) {
            fieldNames.put(field.getName(), ++nextOrder);
        }
    }

}